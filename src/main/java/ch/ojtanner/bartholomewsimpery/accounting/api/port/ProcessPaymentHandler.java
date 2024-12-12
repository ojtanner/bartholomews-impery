package ch.ojtanner.bartholomewsimpery.accounting.api.port;

import io.nats.client.Message;

public interface ProcessPaymentHandler {

    void onMessage(Message message);
}
