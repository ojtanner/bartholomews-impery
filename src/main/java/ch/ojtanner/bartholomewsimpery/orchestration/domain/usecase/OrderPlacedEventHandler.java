package ch.ojtanner.bartholomewsimpery.orchestration.domain.usecase;

import ch.ojtanner.bartholomewsimpery.orchestration.domain.service.SagaOrchestrator;
import ch.ojtanner.bartholomewsimpery.orchestration.api.port.OrderPlacedHandler;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class OrderPlacedEventHandler implements OrderPlacedHandler, MessageHandler {

    private final ObjectMapper objectMapper;
    private final SagaOrchestrator sagaOrchestrator;

    public OrderPlacedEventHandler(
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
            System.out.println("OrderPlacedEventHandler onMessage error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void handle(Order placedOrder) {
        System.out.println("Received OrderPlacedEvent: id: " + placedOrder.getId() + " status: " + placedOrder.getStatus() + " summoningFee: " + placedOrder.getSummoningFee());
        sagaOrchestrator.startSaga(placedOrder);
    }
}
