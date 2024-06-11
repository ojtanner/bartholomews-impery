package ch.ojtanner.bartholomewsimpery.reception.domain.usecase;

import ch.ojtanner.bartholomewsimpery.reception.api.port.RetrieveAllOrdersUseCase;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.reception.infrastructure.port.OrderRepository;
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
