package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;
import ch.ojtanner.bartholomewsimpery.accounting.domain.valueobject.SummoningFee;
import ch.ojtanner.bartholomewsimpery.reception.domain.constant.Currency;
import ch.ojtanner.bartholomewsimpery.schemaRegistry.accounting.AccountingOrderSchema;

public final class PaymentProcessedResponse extends SagaResponse {

    private final OrderStatus orderStatus;
    private final SummoningFee summoningFee;

    private PaymentProcessedResponse(
            String orderId,
            OrderStatus orderStatus,
            SummoningFee summoningFee
    ) {
        super(orderId);

        this.orderStatus = orderStatus;
        this.summoningFee = summoningFee;
    }

    public static PaymentProcessedResponse fromAccountingOrderSchema(AccountingOrderSchema accountingOrder) {
        return new PaymentProcessedResponse(
                accountingOrder.orderId(),
                OrderStatus.valueOf(accountingOrder.orderStatus().toString()),
                new SummoningFee(
                        Currency.valueOf(accountingOrder.summoningFeeSchema().currency().toString()),
                        accountingOrder.summoningFeeSchema().amount()
                )
        );
    }

    public enum OrderStatus {
        FEE_UNPAID,
        FEE_PAID
    }
}
