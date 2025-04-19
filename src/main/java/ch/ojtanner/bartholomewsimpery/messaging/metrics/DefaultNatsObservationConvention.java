package ch.ojtanner.bartholomewsimpery.messaging.metrics;

import io.micrometer.common.KeyValues;

import static ch.ojtanner.bartholomewsimpery.messaging.metrics.NatsObservationDocumentation.NatsLowCardinalityKeyNames.RECEIVER;

public class DefaultNatsObservationConvention implements NatsObservationConvention {

    @Override
    public KeyValues getLowCardinalityKeyValues(NatsSenderContext context) {
        return KeyValues.of(RECEIVER.withValue(context.getRecipient()));
    }

    @Override
    public String getName() {
        return "message.outgoing";
    }
}
