package com.example.model.subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Отображение сущности в подзапрос
 */
@Entity
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.Subselect(
        value = "select i.ID as ITEMID, i.NAME, count(b.ID) as NUMBEROFBIDS " +
                "from ITEM i left outer join BID b on i.ID = b.ITEM_ID " +
                "group by i.ID, i.NAME"
)
/*
 * Все имена таблиц, упомянутые в запросе SELECT, должны быть перечислены
 * в аннотации @org.hibernate.annotations.Synchronize.
 * Тогда Hibernate будет знать, что перед выполнением запроса к ItemBidSummary необходимо синхронизировать
 * изменения в Item и Bid с базой данных.
 */
@org.hibernate.annotations.Synchronize({"Item", "Bid"})
public class ItemBidSummary {
    @Id
    protected Long itemId;
    protected String name;
    protected long numberOfBids;

    public Long getItemId() {
        return itemId;
    }
    public String getName() {
        return name;
    }
    public long getNumberOfBids() {
        return numberOfBids;
    }

    @Override
    public String toString() {
        return "ItemBidSummary{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", numberOfBids=" + numberOfBids +
                '}';
    }
}
