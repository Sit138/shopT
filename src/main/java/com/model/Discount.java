package com.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "discount")
public class Discount {

    public Discount(){
    }

    public Discount(double value, Date startDate, BigDecimal productDiscountPrice, BigDecimal discountPriceSpread, int addType){
        this.value = value;
        this.startDate = startDate;
        this.endDate = null;
        this.productDiscountPrice = productDiscountPrice;
        this.discountPriceSpread = discountPriceSpread;
        this.addType = addType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "discount_value")
    private double value;

    @Column(name = "discount_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "discount_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "product_discount_price")
    private BigDecimal productDiscountPrice;

    @Column(name = "discount_price_spread")
    private BigDecimal discountPriceSpread;

    @Column(name = "add_type")
    private int addType;//1 - auto, 2 - manual

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDiscountValue() {
        return value;
    }

    public void setDiscountValue(double discount_value) {
        this.value = discount_value;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date discount_date) {
        this.startDate = discount_date;
    }

    public int getId() {
        return id;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAddType() {
        return addType;
    }

    public void setAddType(int addType) {
        this.addType = addType;
    }
}
