package ch.ojtanner.bartholomewsimpery.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;

import java.util.Optional;

public interface OrderRepository {

    void save(Order order);
    Optional<Order> findById(int id);
}
