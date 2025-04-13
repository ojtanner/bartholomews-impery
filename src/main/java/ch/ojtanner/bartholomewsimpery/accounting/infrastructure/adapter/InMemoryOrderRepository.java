package ch.ojtanner.bartholomewsimpery.accounting.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.OrderRepository;
import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> repo = new HashMap<>();

    @Override
    public void save(Order order) {
        repo.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(repo.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(repo.values());
    }
}
