package ch.ojtanner.bartholomewsimpery.reception.api.adapter;

import ch.ojtanner.bartholomewsimpery.reception.domain.valueobject.SummoningFee;

import java.util.function.Function;

public interface AccountingSubscriber {

    void subscribe(Function<SummoningFee, Void> callback);
}
