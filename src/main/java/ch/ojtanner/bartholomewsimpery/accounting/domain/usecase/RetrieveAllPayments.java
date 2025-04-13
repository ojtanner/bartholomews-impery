package ch.ojtanner.bartholomewsimpery.accounting.domain.usecase;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.RetrieveAllPaymentsUseCase;
import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveAllPayments implements RetrieveAllPaymentsUseCase {

    private final OrderRepository orderRepository;

    public RetrieveAllPayments(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> handle() {
        return orderRepository.findAll();
    }
}
