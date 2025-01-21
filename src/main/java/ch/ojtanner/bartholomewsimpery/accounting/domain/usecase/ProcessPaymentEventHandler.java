package ch.ojtanner.bartholomewsimpery.accounting.domain.usecase;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.ProcessPaymentHandler;
import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.PaymentProcessedPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProcessPaymentEventHandler implements ProcessPaymentHandler {

    private final ObjectMapper objectMapper;
    private final PaymentProcessedPublisher paymentProcessedPublisher;

    public ProcessPaymentEventHandler(
            ObjectMapper objectMapper,
            PaymentProcessedPublisher paymentProcessedPublisher
    ) {
        this.objectMapper = objectMapper;
        this.paymentProcessedPublisher = paymentProcessedPublisher;
    }

    @Override
    public void onMessage(Message message) {
        try {
            Order orderToBePayed = objectMapper.readValue(message.getData(), Order.class);
            System.out.println("Accounting received message: " + orderToBePayed.getId());
            System.out.println("STUB: Accounting processed payment of order: " + orderToBePayed.getId());
            paymentProcessedPublisher.publish(orderToBePayed);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
