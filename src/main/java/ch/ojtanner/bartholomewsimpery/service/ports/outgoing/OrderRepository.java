package ch.ojtanner.bartholomewsimpery.service.ports.outgoing;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;

import java.util.Optional;

public interface OrderRepository {

    void save(Order order);
    Optional<Order> findById(int id);
}
