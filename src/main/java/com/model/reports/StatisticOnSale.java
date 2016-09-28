package com.model.reports;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "static_sale")
public class StatisticOnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;

    @Column(name = "count_sale_product")
    private int countSaleProduct;

    @Column(name = "sum_sale_product")
    private BigDecimal sumSaleProduct;

    @Column(name = "sum_spread_amount")
    private BigDecimal sumSpreadAmount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getCountSaleProduct() {
        return countSaleProduct;
    }

    public void setCountSaleProduct(int countSaleProduct) {
        this.countSaleProduct = countSaleProduct;
    }

    public BigDecimal getSumSaleProduct() {
        return sumSaleProduct;
    }

    public void setSumSaleProduct(BigDecimal sumSaleProduct) {
        this.sumSaleProduct = sumSaleProduct;
    }

    public BigDecimal getSumSpreadAmount() {
        return sumSpreadAmount;
    }

    public void setSumSpreadAmount(BigDecimal sumSpreadAmount) {
        this.sumSpreadAmount = sumSpreadAmount;
    }

    public int getId() {
        return id;
    }
}
