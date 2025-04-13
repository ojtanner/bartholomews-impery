package ch.ojtanner.bartholomewsimpery.accounting.api.adapter;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.PayOrderUseCase;
import ch.ojtanner.bartholomewsimpery.accounting.api.port.RetrieveAllPaymentsUseCase;
import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.accounting.domain.exception.PaymentFailedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

    private final PayOrderUseCase payOrderUseCase;
    private final RetrieveAllPaymentsUseCase retrieveAllPaymentsUseCase;

    public AccountingController(
            PayOrderUseCase payOrderUseCase,
            RetrieveAllPaymentsUseCase retrieveAllPaymentsUseCase
    ) {
        this.payOrderUseCase = payOrderUseCase;
        this.retrieveAllPaymentsUseCase = retrieveAllPaymentsUseCase;
    }

    @PatchMapping("/order/{orderId}")
    public void payOrder(@PathVariable String orderId) throws PaymentFailedException {
        payOrderUseCase.handle(orderId);
    }

    @GetMapping("/order")
    public List<Order> getOrders() {
        return retrieveAllPaymentsUseCase.handle();
    }
}
