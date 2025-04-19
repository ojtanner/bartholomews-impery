package ch.ojtanner.bartholomewsimpery.accounting.api.adapter;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.RegisterOrderUseCase;
import ch.ojtanner.bartholomewsimpery.orchestration.api.adapter.NatsConnection;
import io.nats.client.Dispatcher;
import org.springframework.stereotype.Service;

@Service
public class AccountingNatsSubscriber {

    private final NatsConnection natsConnection;
    private final RegisterOrderUseCase registerOrderHandler;

    public AccountingNatsSubscriber(
            NatsConnection natsConnection,
            RegisterOrderUseCase registerOrderHandler
    ) {
        this.natsConnection = natsConnection;
        this.registerOrderHandler = registerOrderHandler;

        subscribeToOrderCreatedEvent();
    }

    private void subscribeToOrderCreatedEvent() {
        final String topicName = "register-standing-order-payment";
        Dispatcher dispatcher = natsConnection.getConnection().createDispatcher();
        dispatcher.subscribe(topicName, registerOrderHandler::handle);
    }
}
