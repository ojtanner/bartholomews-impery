package ch.ojtanner.bartholomewsimpery.orchestration.api.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.api.port.OrderPlacedHandler;
import io.nats.client.Dispatcher;
import org.springframework.stereotype.Service;

@Service
public class OrchestratorNatsSubscriber {

    private final NatsConnection natsConnection;
    private final OrderPlacedHandler orderPlacedHandler;

    public OrchestratorNatsSubscriber(
            NatsConnection natsConnection,
            OrderPlacedHandler orderPlacedHandler
    ) {
        this.natsConnection = natsConnection;
        this.orderPlacedHandler = orderPlacedHandler;

        subscribeToOrderCreatedEvent();
    }

    private void subscribeToOrderCreatedEvent() {
        final String topicName = "order-created";
        Dispatcher dispatcher = natsConnection.getConnection().createDispatcher();
        dispatcher.subscribe(topicName, orderPlacedHandler::onMessage);
    }
}
