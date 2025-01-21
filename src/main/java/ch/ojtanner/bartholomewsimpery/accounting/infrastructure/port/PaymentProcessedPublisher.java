package ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public interface PaymentProcessedPublisher {

    void publish(Order order);

}
