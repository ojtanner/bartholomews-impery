package ch.ojtanner.bartholomewsimpery.messaging.metrics;

import io.micrometer.common.docs.KeyName;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;
import io.micrometer.observation.docs.ObservationDocumentation;

public enum NatsObservationDocumentation implements ObservationDocumentation {

    SEND {
        @Override
        public Class<? extends ObservationConvention<? extends Observation.Context>> getDefaultConvention() {
            return NatsObservationConvention.class;
        }

        @Override
        public KeyName[] getLowCardinalityKeyNames() {
            return NatsLowCardinalityKeyNames.values();
        }
    };

    enum NatsLowCardinalityKeyNames implements KeyName {

        RECEIVER {
            @Override
            public String asString() {
                return "message.receiver";
            }
        }
    }
}
