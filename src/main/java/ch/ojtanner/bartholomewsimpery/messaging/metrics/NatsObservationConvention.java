package ch.ojtanner.bartholomewsimpery.messaging.metrics;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;

public interface NatsObservationConvention extends ObservationConvention<NatsSenderContext> {

    @Override
    default boolean supportsContext(Observation.Context context) {
        return context instanceof NatsSenderContext;
    }
}
