package ch.ojtanner.bartholomewsimpery.accounting.domain.entity;

import ch.ojtanner.bartholomewsimpery.accounting.domain.constant.OrderStatus;
import ch.ojtanner.bartholomewsimpery.accounting.domain.valueobject.SummoningFee;

public class Order extends Entity {

    private OrderStatus status;
    private final SummoningFee summoningFee;

    public Order(String id, SummoningFee summoningFee) {
        super(id);
        this.summoningFee = summoningFee;
        this.status = OrderStatus.FEE_UNPAID;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public SummoningFee getSummoningFee() {
        return this.summoningFee;
    }

    public void feePayed() {
        if (this.status != OrderStatus.FEE_UNPAID) return;
        this.status = OrderStatus.FEE_PAID;
    }

    public static Order fromReceptionOrder(ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order receptionOrder) {
        return new Order(
                receptionOrder.getId(),
                new SummoningFee(
                        receptionOrder.getSummoningFee().getCurrency(),
                        receptionOrder.getSummoningFee().getAmount()
                )
        );
    }
}
