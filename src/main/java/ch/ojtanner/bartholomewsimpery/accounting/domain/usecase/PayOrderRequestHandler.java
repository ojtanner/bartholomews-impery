package ch.ojtanner.bartholomewsimpery.accounting.domain.usecase;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.PayOrderHandler;
import ch.ojtanner.bartholomewsimpery.accounting.domain.exception.PaymentFailedException;
import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.OrderRepository;
import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.accounting.infrastructure.port.PaymentProcessedPublisher;
import org.springframework.stereotype.Component;

@Component
public class PayOrderRequestHandler implements PayOrderHandler {

    private final OrderRepository orderRepository;
    private final PaymentProcessedPublisher paymentProcessedPublisher;

    public PayOrderRequestHandler(
            OrderRepository orderRepository,
            PaymentProcessedPublisher paymentProcessedPublisher
    ) {
        this.orderRepository = orderRepository;
        this.paymentProcessedPublisher = paymentProcessedPublisher;
    }

    @Override
    public void payOrder(String orderId) throws PaymentFailedException {
        Order orderToPay = orderRepository.findById(orderId).orElseThrow(PaymentFailedException::new);

        if (Math.random() < 0.5) {
            throw new PaymentFailedException();
        }

        orderToPay.feePayed();
        orderRepository.save(orderToPay);
        paymentProcessedPublisher.publish(orderToPay);
    }
}
