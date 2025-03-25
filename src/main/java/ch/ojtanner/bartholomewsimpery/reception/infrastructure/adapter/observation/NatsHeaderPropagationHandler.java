package ch.ojtanner.bartholomewsimpery.reception.infrastructure.adapter.observation;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;

public class NatsHeaderPropagationHandler implements ObservationHandler<NatsSenderContext> {

    @Override
    public void onStart(NatsSenderContext context) {
        ObservationHandler.super.onStart(context);
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return context instanceof NatsSenderContext;
    }
}
