package ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(String id);
}
