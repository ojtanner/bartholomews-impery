package ch.ojtanner.bartholomewsimpery.orchestration.domain.usecase;

import ch.ojtanner.bartholomewsimpery.orchestration.api.port.PaymentProcessedHandler;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.service.SagaOrchestrator;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject.PaymentProcessedResponse;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject.SagaResponse;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymentProcessedEventHandler implements PaymentProcessedHandler {

    private final ObjectMapper objectMapper;
    private final SagaOrchestrator sagaOrchestrator;

    public PaymentProcessedEventHandler(
            ObjectMapper objectMapper,
            SagaOrchestrator sagaOrchestrator
    ) {
        this.objectMapper = objectMapper;
        this.sagaOrchestrator = sagaOrchestrator;
    }

    @Override
    public void onMessage(Message msg) {
        try {
            Order placedOrder = objectMapper.readValue(msg.getData(), Order.class);
            this.handle(placedOrder);

        } catch (IOException e) {
            System.out.println("PaymentProcessed onMessage error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void handle(Order paymentProcessedOrder) {
        System.out.println("Received PaymentProcessed: id: " + paymentProcessedOrder.getId() + " status: " + paymentProcessedOrder.getStatus() + " summoningFee: " + paymentProcessedOrder.getSummoningFee());
        SagaResponse sagaResponse = new PaymentProcessedResponse(paymentProcessedOrder);
        sagaOrchestrator.handleResponse(sagaResponse);
    }
}
