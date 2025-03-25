package ch.ojtanner.bartholomewsimpery.accounting.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.reception.domain.constant.Currency;

public class SummoningFee implements ValueObject {

    private final Currency currency;
    private final int amount;

    public SummoningFee(Currency currency, int amount) {
        if (amount < 0) throw new IllegalArgumentException("SummoningFee amount must be 0 or more");

        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(ValueObject vo2) {
        if (!(vo2 instanceof SummoningFee sf2)) return false;

        if (this.currency != sf2.currency) return false;
        return this.amount == sf2.getAmount();
    }
}
