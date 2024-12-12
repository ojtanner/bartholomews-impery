package ch.ojtanner.bartholomewsimpery.accounting.api.adapter;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.ProcessPaymentHandler;
import ch.ojtanner.bartholomewsimpery.orchestration.api.adapter.NatsConnection;
import io.nats.client.Dispatcher;
import org.springframework.stereotype.Service;

@Service
public class AccountingNatsSubscriber {

    private final NatsConnection natsConnection;
    private final ProcessPaymentHandler processPaymentHandler;

    public AccountingNatsSubscriber(
            NatsConnection natsConnection,
            ProcessPaymentHandler processPaymentHandler
    ) {
        this.natsConnection = natsConnection;
        this.processPaymentHandler = processPaymentHandler;

        subscribeToOrderCreatedEvent();
    }

    private void subscribeToOrderCreatedEvent() {
        final String topicName = "process-payment";
        Dispatcher dispatcher = natsConnection.getConnection().createDispatcher();
        dispatcher.subscribe(topicName, processPaymentHandler::onMessage);
    }
}
