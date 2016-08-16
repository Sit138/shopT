package com.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "Discount")
public class Discount {

    public Discount(){

    }

    public Discount(double discount_value, Date discount_date, BigDecimal productDiscountPrice, BigDecimal discountPriceSpread){
        this.discountValue = discount_value;
        this.discountDate = discount_date;
        this.productDiscountPrice = productDiscountPrice;
        this.discountPriceSpread = discountPriceSpread;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "discount_value")
    private double discountValue;

    @Column(name = "discount_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date discountDate;

    @Column(name = "product_discount_price")
    private BigDecimal productDiscountPrice;

    @Column(name = "discount_price_spread")
    private BigDecimal discountPriceSpread;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDiscount_value() {
        return discountValue;
    }

    public void setDiscount_value(double discount_value) {
        this.discountValue = discount_value;
    }

    public Date getDiscount_date() {
        return discountDate;
    }

    public void setDiscount_date(Date discount_date) {
        this.discountDate = discount_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(BigDecimal productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public BigDecimal getDiscountPriceSpread() {
        return discountPriceSpread;
    }

    public void setDiscountPriceSpread(BigDecimal discountPriceSpread) {
        this.discountPriceSpread = discountPriceSpread;
    }
}
