package ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;

public interface PaymentProcessedPublisher {

    void publish(Order order);

}
