package ch.ojtanner.bartholomewsimpery.orchestration.api.port;

import io.nats.client.Message;

public interface OrderPlacedHandler {

    void onMessage(Message message);

}
