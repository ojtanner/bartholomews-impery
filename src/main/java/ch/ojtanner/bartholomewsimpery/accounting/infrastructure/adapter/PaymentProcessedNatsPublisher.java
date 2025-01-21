package ch.ojtanner.bartholomewsimpery.accounting.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.PaymentProcessedPublisher;
import ch.ojtanner.bartholomewsimpery.orchestration.api.adapter.NatsConnection;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessedNatsPublisher implements PaymentProcessedPublisher {

    private final NatsConnection natsConnection;
    private final ObjectMapper objectMapper;

    public PaymentProcessedNatsPublisher(NatsConnection natsConnection, ObjectMapper objectMapper) {
        this.natsConnection = natsConnection;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(Order order) {
        try {
            byte[] message = objectMapper.writeValueAsBytes(order);
            String topicName = "process-payment.response";
            System.out.println("Publishing " + topicName);
            natsConnection.getConnection().publish(topicName, message);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
