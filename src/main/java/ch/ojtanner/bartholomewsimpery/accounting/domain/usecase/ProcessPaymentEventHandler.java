package ch.ojtanner.bartholomewsimpery.accounting.domain.usecase;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.ProcessPaymentHandler;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProcessPaymentEventHandler implements ProcessPaymentHandler {

    private final ObjectMapper objectMapper;

    public ProcessPaymentEventHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message) {
        try {
            Order orderToBePayed = objectMapper.readValue(message.getData(), Order.class);
            System.out.println("Accounting received message: " + orderToBePayed.getId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
