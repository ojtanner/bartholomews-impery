package ch.ojtanner.bartholomewsimpery.reception.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.valueobject.SummoningFee;

public interface AccountingPublisher {

    void publish(SummoningFee summoningFee);
}
