package ch.ojtanner.bartholomewsimpery.accounting.api.port;

import io.nats.client.Message;

public interface RegisterOrderHandler {

    void onMessage(Message message);
}
