package ch.ojtanner.bartholomewsimpery.domain.usecase;

import ch.ojtanner.bartholomewsimpery.api.port.RetrieveAllOrdersUseCase;
import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveAllOrders implements RetrieveAllOrdersUseCase {

    private final OrderRepository orderRepository;

    public RetrieveAllOrders(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> handle() {
        return orderRepository.findAll();
    }
}
