package ch.ojtanner.bartholomewsimpery.accounting.api.port;

import ch.ojtanner.bartholomewsimpery.accounting.domain.exception.PaymentFailedException;

public interface PayOrderUseCase {

    void handle(String orderId) throws PaymentFailedException;
}
