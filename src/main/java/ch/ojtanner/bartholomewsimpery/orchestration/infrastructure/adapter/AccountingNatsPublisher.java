package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.api.adapter.NatsConnection;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.AccountingCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.reception.infrastructure.port.OrderPublisher;
import ch.ojtanner.bartholomewsimpery.schemaRegistry.accounting.AccountingOrderSchema;
import ch.ojtanner.bartholomewsimpery.schemaRegistry.reception.ReceptionOrderSchema;
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
    public void publishProcessPaymentCommand(ReceptionOrderSchema order) {
        try {
            AccountingOrderSchema accountingOrder = new AccountingOrderSchema(
                    order.orderId(),
                    null,
                    order.summoningFee()
            );
            byte[] message = objectMapper.writeValueAsBytes(accountingOrder);
            String topicName = "register-standing-order-payment";
            natsConnection.getConnection().publish(topicName, message);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
