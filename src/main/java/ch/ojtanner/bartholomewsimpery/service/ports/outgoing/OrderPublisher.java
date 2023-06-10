package ch.ojtanner.bartholomewsimpery.service.ports.outgoing;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;

public interface OrderPublisher {

    void publish(Order order);
}
