package ch.ojtanner.bartholomewsimpery.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;

public interface OrderPublisher {

    void publish(Order order);
}
