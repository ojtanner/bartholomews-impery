package ch.ojtanner.bartholomewsimpery.orchestration.api.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.api.port.OrderPlacedHandler;
import ch.ojtanner.bartholomewsimpery.orchestration.api.port.PaymentProcessedHandler;
import io.nats.client.Dispatcher;
import org.springframework.stereotype.Service;

@Service
public class OrchestratorNatsSubscriber {

    private final NatsConnection natsConnection;
    private final OrderPlacedHandler orderPlacedHandler;
    private final PaymentProcessedHandler paymentProcessedHandler;

    public OrchestratorNatsSubscriber(
            NatsConnection natsConnection,
            OrderPlacedHandler orderPlacedHandler,
            PaymentProcessedHandler paymentProcessedHandler
    ) {
        this.natsConnection = natsConnection;
        this.orderPlacedHandler = orderPlacedHandler;
        this.paymentProcessedHandler = paymentProcessedHandler;

        subscribeToOrderCreatedEvent();
        subscribeToPaymentProcessedEvent();
    }

    private void subscribeToOrderCreatedEvent() {
        final String topicName = "order-created";
        Dispatcher dispatcher = natsConnection.getConnection().createDispatcher();
        dispatcher.subscribe(topicName, orderPlacedHandler::onMessage);
    }

    private void subscribeToPaymentProcessedEvent() {
        final String topicName = "process-payment.response";
        Dispatcher dispatcher = natsConnection.getConnection().createDispatcher();
        dispatcher.subscribe(topicName, paymentProcessedHandler::onMessage);
    }
}
