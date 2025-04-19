package ch.ojtanner.bartholomewsimpery.messaging.metrics;

import io.micrometer.observation.Observation;
import io.nats.client.Message;

// We do not get access to the NATS Message when publishing, therefore a Context suffices
public class NatsSenderContext extends Observation.Context {

    private String recipient;

    public NatsSenderContext(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }
}
