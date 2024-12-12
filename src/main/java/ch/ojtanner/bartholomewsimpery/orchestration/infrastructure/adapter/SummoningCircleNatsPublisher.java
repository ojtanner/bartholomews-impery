package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.SummoningCircleCommandPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class SummoningCircleNatsPublisher implements SummoningCircleCommandPublisher {
    @Override
    public void publishStartSummoningCommand(Order order) {

    }
}
