package ch.ojtanner.bartholomewsimpery.accounting.domain.usecase;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.RegisterOrderHandler;
import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.OrderRepository;
import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegisterOrderMessageHandler implements RegisterOrderHandler {

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    public RegisterOrderMessageHandler(
            ObjectMapper objectMapper,
            OrderRepository orderRepository
    ) {
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public void onMessage(Message message) {
        try {
            ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order orderToBePayed = objectMapper.readValue(message.getData(), ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order.class);
            Order domainOrder = Order.fromReceptionOrder(orderToBePayed);
            System.out.println("Accounting received message: " + orderToBePayed.getId());
            orderRepository.save(domainOrder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
