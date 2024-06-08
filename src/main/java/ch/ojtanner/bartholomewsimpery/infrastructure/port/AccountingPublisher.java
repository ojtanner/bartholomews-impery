package ch.ojtanner.bartholomewsimpery.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;

public interface AccountingPublisher {

    void publish(SummoningFee summoningFee);
}
