package ch.ojtanner.bartholomewsimpery.orchestration;

import ch.ojtanner.bartholomewsimpery.orchestration.domain.constant.SagaOrchestratorState;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.entity.SagaState;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject.*;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.AccountingCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.ReceptionCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.SagaStateRepository;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.SummoningCircleCommandPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

import java.time.LocalDateTime;

public class SagaOrchestrator {

    private final SagaStateRepository sagaStateRepository;
    private final ReceptionCommandsPublisher receptionCommandsPublisher;
    private final AccountingCommandsPublisher accountingCommandsPublisher;
    private final SummoningCircleCommandPublisher summoningCircleCommandPublisher;

    private final Order orderCopy;

    public SagaOrchestrator(
            SagaStateRepository sagaStateRepository,
            ReceptionCommandsPublisher receptionCommandsPublisher,
            AccountingCommandsPublisher accountingCommandsPublisher,
            SummoningCircleCommandPublisher summoningCircleCommandPublisher,
            Order order
    ) {
        this.sagaStateRepository = sagaStateRepository;
        this.receptionCommandsPublisher = receptionCommandsPublisher;
        this.accountingCommandsPublisher = accountingCommandsPublisher;
        this.summoningCircleCommandPublisher = summoningCircleCommandPublisher;
        this.orderCopy = new Order(order.getId(), order.getSummoningFee());
    }

    public void startSaga(Order order) {
        SagaState sagaState = new SagaState(order.getId());
        sagaStateRepository.save(sagaState);
        accountingCommandsPublisher.publishProcessPaymentCommand(order);
    }

    public void handleResponse(SagaResponse event) {
        SagaState sagaState = sagaStateRepository.
                findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("SagaState of id " + event.getId() + " not found"));

        switch (event) {
            case PaymentProcessedResponse response -> {
                if (sagaState.getState() != SagaOrchestratorState.ORDER_CREATED) {
                    return;
                }

                sagaState.advanceState();
                summoningCircleCommandPublisher.publishStartSummoningCommand(orderCopy);

            }
            case ImpSummonedResponse response -> {
                if (sagaState.getState() != SagaOrchestratorState.PAYMENT_PROCESSED) {
                    return;
                }

                sagaState.advanceState();
                receptionCommandsPublisher.publishDeliverImpCommand(orderCopy);
            }
            case ImpDeliveredResponse response -> {
                if (sagaState.getState() != SagaOrchestratorState.SUMMONING_FINISHED) {
                    return;
                }

                sagaState.advanceState();
                receptionCommandsPublisher.publishDeliverImpCommand(orderCopy);
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
