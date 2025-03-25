package ch.ojtanner.bartholomewsimpery.accounting.api.port;

import ch.ojtanner.bartholomewsimpery.accounting.domain.exception.PaymentFailedException;

public interface PayOrderHandler {

    void payOrder(String orderId) throws PaymentFailedException;
}
