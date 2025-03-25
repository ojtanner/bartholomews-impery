package ch.ojtanner.bartholomewsimpery.accounting.api.adapter;

import ch.ojtanner.bartholomewsimpery.accounting.api.port.PayOrderHandler;
import ch.ojtanner.bartholomewsimpery.accounting.domain.exception.PaymentFailedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

    private final PayOrderHandler payOrderHandler;

    public AccountingController(PayOrderHandler payOrderHandler) {
        this.payOrderHandler = payOrderHandler;
    }

    @PatchMapping("/{orderId}")
    public void payOrder(@PathVariable String orderId) throws PaymentFailedException {
        payOrderHandler.payOrder(orderId);
    }
}
