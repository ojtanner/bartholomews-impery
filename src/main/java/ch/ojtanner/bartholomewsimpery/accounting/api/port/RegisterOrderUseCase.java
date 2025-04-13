package ch.ojtanner.bartholomewsimpery.accounting.api.port;

import io.nats.client.Message;

public interface RegisterOrderUseCase {

    void handle(Message message);
}
