package ch.ojtanner.bartholomewsimpery.accounting.api.adapter;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.RegisterOrderHandler;
import ch.ojtanner.bartholomewsimpery.orchestration.api.adapter.NatsConnection;
import io.nats.client.Dispatcher;
import org.springframework.stereotype.Service;

@Service
public class AccountingNatsSubscriber {

    private final NatsConnection natsConnection;
    private final RegisterOrderHandler registerOrderHandler;

    public AccountingNatsSubscriber(
            NatsConnection natsConnection,
            RegisterOrderHandler registerOrderHandler
    ) {
        this.natsConnection = natsConnection;
        this.registerOrderHandler = registerOrderHandler;

        subscribeToOrderCreatedEvent();
    }

    private void subscribeToOrderCreatedEvent() {
        final String topicName = "process-payment";
        Dispatcher dispatcher = natsConnection.getConnection().createDispatcher();
        dispatcher.subscribe(topicName, registerOrderHandler::onMessage);
    }
}
