package ch.ojtanner.bartholomewsimpery.orchestration.domain.usecase;

import ch.ojtanner.bartholomewsimpery.orchestration.api.port.PaymentProcessedHandler;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.service.SagaOrchestrator;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject.PaymentProcessedResponse;
import ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject.SagaResponse;
import ch.ojtanner.bartholomewsimpery.schemaRegistry.accounting.AccountingOrderSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymentProcessedEventHandler implements PaymentProcessedHandler {

    private final ObjectMapper objectMapper;
    private final SagaOrchestrator sagaOrchestrator;

    public PaymentProcessedEventHandler(
            ObjectMapper objectMapper,
            SagaOrchestrator sagaOrchestrator
    ) {
        this.objectMapper = objectMapper;
        this.sagaOrchestrator = sagaOrchestrator;
    }

    @Override
    public void onMessage(Message msg) {
        try {
            AccountingOrderSchema accountingOrder = objectMapper.readValue(msg.getData(), AccountingOrderSchema.class);
            this.handle(accountingOrder);

        } catch (IOException e) {
            System.out.println("PaymentProcessed onMessage error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void handle(AccountingOrderSchema accountingOrderSchema) {
        System.out.println("Received PaymentProcessed: id: " + accountingOrderSchema.orderId() + " status: " + accountingOrderSchema.orderStatus() + " summoningFee: " + accountingOrderSchema.summoningFeeSchema());
        SagaResponse sagaResponse = PaymentProcessedResponse.fromAccountingOrderSchema(accountingOrderSchema);
        sagaOrchestrator.handleResponse(sagaResponse);
    }
}
