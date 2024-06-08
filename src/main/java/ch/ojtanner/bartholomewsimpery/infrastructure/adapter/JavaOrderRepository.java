package ch.ojtanner.bartholomewsimpery.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JavaOrderRepository implements OrderRepository {

    private final Map<String, Order> orderRepository = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void save(Order order) {
        if (orderRepository.containsKey(order.getId())) {
            System.out.println("Order if ID " + order.getId() + " already exists");
            return;
        }

        orderRepository.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orderRepository.get(id));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.values().stream().toList();
    }
}
