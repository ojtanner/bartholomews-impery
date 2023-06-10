package ch.ojtanner.bartholomewsimpery.service.ports.incoming;

import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;

import java.util.function.Function;

public interface AccountingSubscriber {

    void subscribe(Function<SummoningFee, Void> callback);
}
