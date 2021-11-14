package com.wyh.darkhorse.auction.infrastructure.db.entity;

import com.wyh.darkhorse.auction.payment.Margin;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
//@Table(name = "margion")
public class MarginEntity {
    @Id
    private String id;

    public static MarginEntity from(Margin margin) {
        return new MarginEntity();
    }
}
