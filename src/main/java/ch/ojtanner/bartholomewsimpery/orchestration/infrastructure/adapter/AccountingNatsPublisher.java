package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.api.adapter.NatsConnection;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.AccountingCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.reception.infrastructure.port.OrderPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AccountingNatsPublisher implements AccountingCommandsPublisher {

    private final NatsConnection natsConnection;
    private final ObjectMapper objectMapper;

    public AccountingNatsPublisher(NatsConnection natsConnection, ObjectMapper objectMapper) {
        this.natsConnection = natsConnection;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishProcessPaymentCommand(Order order) {
        try {
            byte[] message = objectMapper.writeValueAsBytes(order);
            String topicName = "process-payment";
            natsConnection.getConnection().publish(topicName, message);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
