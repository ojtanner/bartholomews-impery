package ch.ojtanner.bartholomewsimpery.reception.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);
    Optional<Order> findById(String id);
    List<Order> findAll();
}
