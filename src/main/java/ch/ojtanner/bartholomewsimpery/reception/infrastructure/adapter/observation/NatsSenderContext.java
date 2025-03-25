package ch.ojtanner.bartholomewsimpery.reception.infrastructure.adapter.observation;

import io.micrometer.observation.transport.SenderContext;
import io.nats.client.impl.Headers;

public class NatsSenderContext extends SenderContext<Headers> {

    public NatsSenderContext(Headers headers) {
        super(((carrier, key, value) -> carrier.add(key, value)));
        setCarrier(headers);
    }
}
