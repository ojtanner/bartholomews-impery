package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.ReceptionCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderNatsPublisher implements ReceptionCommandsPublisher {
    @Override
    public void publishDeliverImpCommand(Order order) {
        System.out.println("Delivering Imp of order " + order.getId());
    }
}
