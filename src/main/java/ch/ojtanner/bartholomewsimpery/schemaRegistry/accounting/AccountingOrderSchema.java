package ch.ojtanner.bartholomewsimpery.schemaRegistry.accounting;

import ch.ojtanner.bartholomewsimpery.schemaRegistry.SummoningFeeSchema;

public record AccountingOrderSchema(String orderId, OrderStatus orderStatus, SummoningFeeSchema summoningFeeSchema) {

    public enum OrderStatus {
        FEE_UNPAID,
        FEE_PAID
    }
}
