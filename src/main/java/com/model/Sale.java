package com.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sale_amount")
    private BigDecimal amount;

    @Column(name = "price_product")
    private BigDecimal priceProduct;

    @Column(name = "spread_price_amount")
    private BigDecimal spreadPriceAmount;//разница между реальной суммой продукта и суммой продажи продукта (возможно со скидкой)

    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Sale(){
    }

    public Sale(BigDecimal amount, BigDecimal priceProduct, Date date){
        this.amount = amount;
        this.priceProduct = priceProduct;
        this.spreadPriceAmount = priceProduct.subtract(amount);
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }

    public BigDecimal getSpreadPriceAmount() {
        return spreadPriceAmount;
    }

    public void setSpreadPriceAmount(BigDecimal spreadPriceAmount) {
        this.spreadPriceAmount = spreadPriceAmount;
    }
}
