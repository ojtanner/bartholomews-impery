package ch.ojtanner.bartholomewsimpery.schemaRegistry.reception;


import ch.ojtanner.bartholomewsimpery.schemaRegistry.SummoningFeeSchema;

public record ReceptionOrderSchema(String orderId, OrderStatus orderStatus, SummoningFeeSchema summoningFee) {

    public enum OrderStatus {
        PLACED,
        IN_PROGRESS,
        SUCCEEDED,
        FAILED
    }
}
