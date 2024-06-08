package ch.ojtanner.bartholomewsimpery.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.AccountingPublisher;
import org.springframework.stereotype.Component;

@Component
public class StdoutAccountingPublisher implements AccountingPublisher {
    @Override
    public void publish(SummoningFee summoningFee) {
        System.out.println("Publishing summoning fee to stdout: " + summoningFee);
    }
}
