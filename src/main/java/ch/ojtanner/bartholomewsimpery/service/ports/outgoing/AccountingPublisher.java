package ch.ojtanner.bartholomewsimpery.service.ports.outgoing;

import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;

public interface AccountingPublisher {

    void publish(SummoningFee summoningFee);
}
