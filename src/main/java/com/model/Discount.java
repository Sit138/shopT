package com.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    // TODO: Kirill discount и так далее и так далее 
    @Column(name = "discount_value")
    @Getter @Setter
    private double value;

    @Column(name = "discount_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date startDate;

    @Column(name = "discount_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date endDate;

    // TODO: Kirill не надо комментарием с пояснением, сделай так чтоб не забыть никогда. Перечисление 
    @Column(name = "add_type")
    @Getter @Setter
    private int addType;//1 - auto, 2 - manual

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter
    private Product product;

    public Discount() {
    }

    public Discount(double value, Date startDate, int addType) {
        this.value = value;
        this.startDate = startDate;
        this.endDate = null;
        this.addType = addType;
    }

}
