package com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "sale_amount")
    @Getter @Setter
    private BigDecimal amount;

    @Column(name = "price_product")
    @Getter @Setter
    private BigDecimal priceProduct;

    @Column(name = "spread_price_amount")
    @Getter @Setter
    private BigDecimal spreadPriceAmount;

    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter
    private Product product;

    public Sale(){
    }

    public Sale(BigDecimal amount, BigDecimal priceProduct, Date date){
        this.amount = amount;
        this.priceProduct = priceProduct;
        this.spreadPriceAmount = priceProduct.subtract(amount);
        this.date = date;
    }

}
