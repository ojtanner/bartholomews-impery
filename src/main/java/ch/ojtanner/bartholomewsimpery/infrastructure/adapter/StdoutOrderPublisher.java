package ch.ojtanner.bartholomewsimpery.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.OrderPublisher;
import org.springframework.stereotype.Component;

@Component
public class StdoutOrderPublisher implements OrderPublisher {
    @Override
    public void publish(Order order) {
        System.out.println("Publishing order to stdout: " + order);
    }
}
