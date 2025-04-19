package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.schemaRegistry.reception.ReceptionOrderSchema;

public interface AccountingCommandsPublisher {

    void publishProcessPaymentCommand(ReceptionOrderSchema order);
}
