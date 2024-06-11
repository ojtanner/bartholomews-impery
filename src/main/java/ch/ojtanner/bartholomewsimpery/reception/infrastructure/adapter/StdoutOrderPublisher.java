package ch.ojtanner.bartholomewsimpery.reception.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.reception.infrastructure.port.OrderPublisher;
import org.springframework.stereotype.Component;

@Component
public class StdoutOrderPublisher implements OrderPublisher {
    @Override
    public void publish(Order order) {
        System.out.println("Publishing order to stdout: " + order);
    }
}
