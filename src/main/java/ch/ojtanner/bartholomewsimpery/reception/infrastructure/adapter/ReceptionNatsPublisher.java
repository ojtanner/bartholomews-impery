package ch.ojtanner.bartholomewsimpery.reception.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.api.adapter.NatsConnection;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.reception.infrastructure.port.OrderPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ReceptionNatsPublisher implements OrderPublisher {

    private final NatsConnection natsConnection;
    private final ObjectMapper objectMapper;

    public ReceptionNatsPublisher(
            NatsConnection natsConnection,
            ObjectMapper objectMapper
    ) {
        this.natsConnection = natsConnection;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(Order order) {
        try {
            byte[] message = objectMapper.writeValueAsBytes(order);
            String topicName = "order-created";
            natsConnection.getConnection().publish(topicName, message);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
