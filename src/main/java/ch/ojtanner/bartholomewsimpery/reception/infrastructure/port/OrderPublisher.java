package ch.ojtanner.bartholomewsimpery.reception.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public interface OrderPublisher {

    void publish(Order order);
}
