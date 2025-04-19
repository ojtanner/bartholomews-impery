package ch.ojtanner.bartholomewsimpery.accounting.domain.usecase;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.RegisterOrderUseCase;
import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.OrderRepository;
import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.schemaRegistry.accounting.AccountingOrderSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegisterOrder implements RegisterOrderUseCase {

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    public RegisterOrder(
            ObjectMapper objectMapper,
            OrderRepository orderRepository
    ) {
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(Message message) {
        try {
            AccountingOrderSchema accountingOrder = objectMapper.readValue(message.getData(), AccountingOrderSchema.class);
            Order domainOrder = Order.fromSchemaRegistry(accountingOrder);
            System.out.println("Accounting received message: " + domainOrder.getId());
            orderRepository.save(domainOrder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
