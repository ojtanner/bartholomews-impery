package ch.ojtanner.bartholomewsimpery.schemaRegistry;


public record SummoningFeeSchema(Currency currency, int amount) {

    public enum Currency {
        GOLD
    }
}

