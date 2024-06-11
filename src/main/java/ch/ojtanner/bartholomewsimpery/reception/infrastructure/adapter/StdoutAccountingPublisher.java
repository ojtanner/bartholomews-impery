package ch.ojtanner.bartholomewsimpery.reception.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.reception.domain.valueobject.SummoningFee;
import ch.ojtanner.bartholomewsimpery.reception.infrastructure.port.AccountingPublisher;
import org.springframework.stereotype.Component;

@Component
public class StdoutAccountingPublisher implements AccountingPublisher {
    @Override
    public void publish(SummoningFee summoningFee) {
        System.out.println("Publishing summoning fee to stdout: " + summoningFee);
    }
}
