package ch.ojtanner.bartholomewsimpery.orchestration.api.port;

import io.nats.client.Message;

public interface PaymentProcessedHandler {

    void onMessage(Message message);
}
