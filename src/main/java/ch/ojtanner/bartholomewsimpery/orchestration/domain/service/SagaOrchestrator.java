package ch.ojtanner.bartholomewsimpery.orchestration.domain.service;

import ch.ojtanner.bartholomewsimpery.orchestration.domain.constant.SagaOrchestratorState;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.entity.SagaState;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject.*;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.AccountingCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.ReceptionCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.SagaStateRepository;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.SummoningCircleCommandPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.schemaRegistry.reception.ReceptionOrderSchema;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SagaOrchestrator {

    private final SagaStateRepository sagaStateRepository;
    private final ReceptionCommandsPublisher receptionCommandsPublisher;
    private final AccountingCommandsPublisher accountingCommandsPublisher;
    private final SummoningCircleCommandPublisher summoningCircleCommandPublisher;

    public SagaOrchestrator(
            SagaStateRepository sagaStateRepository,
            ReceptionCommandsPublisher receptionCommandsPublisher,
            AccountingCommandsPublisher accountingCommandsPublisher,
            SummoningCircleCommandPublisher summoningCircleCommandPublisher
    ) {
        this.sagaStateRepository = sagaStateRepository;
        this.receptionCommandsPublisher = receptionCommandsPublisher;
        this.accountingCommandsPublisher = accountingCommandsPublisher;
        this.summoningCircleCommandPublisher = summoningCircleCommandPublisher;
    }

    public void startSaga(ReceptionOrderSchema order) {
        System.out.println("Starting Saga " + order.orderId());
        System.out.println("Verifying if Saga already exists: " + order.orderId());
        Optional<SagaState> maybeSagaState = sagaStateRepository.findById(order.orderId());
        SagaState sagaState;

        if (maybeSagaState.isPresent()) {
            System.out.println("Saga already exists: " + order.orderId() + ", no-op");
            return;
        }

        System.out.println("Saga does not exist: " + order.orderId() + ". Proceeding.");
        sagaState = new SagaState(order.orderId());
        sagaStateRepository.save(sagaState);
        accountingCommandsPublisher.publishProcessPaymentCommand(order);
    }

    public void handleResponse(SagaResponse event) {
        String orderId = event.getOrderId();
        SagaState sagaState = sagaStateRepository
                .findById(orderId)
                .orElseThrow(() -> new IllegalStateException("SagaState of id " + orderId + " not found"));

        System.out.println("Orchestrator for saga " + orderId + " received new event. Current state: " + sagaState);

        switch (event) {
            case PaymentProcessedResponse response -> {
                if (sagaState.getState() != SagaOrchestratorState.ORDER_CREATED) {
                    return;
                }

                System.out.println("Payment processed event received. : " + orderId);
                sagaState.advanceState();
                System.out.println("New saga state: " + sagaState.getState());
                //summoningCircleCommandPublisher.publishStartSummoningCommand(orderOfEvent);

            }
            case ImpSummonedResponse response -> {
                if (sagaState.getState() != SagaOrchestratorState.PAYMENT_PROCESSED) {
                    return;
                }

                sagaState.advanceState();
                //receptionCommandsPublisher.publishDeliverImpCommand(orderOfEvent);
            }
            case ImpDeliveredResponse response -> {
                if (sagaState.getState() != SagaOrchestratorState.SUMMONING_FINISHED) {
                    return;
                }

                sagaState.advanceState();
                //receptionCommandsPublisher.publishDeliverImpCommand(orderOfEvent);
            }
            case OrderCompletedResponse response -> {
                if (sagaState.getState() != SagaOrchestratorState.IMP_DELIVERED) {
                    return;
                }

                sagaState.advanceState();
                // Fin
            }
        }
    }
}
