package com.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StatisticOnSaleDTO {

    private String productName;
    private Timestamp salePeriod;
    private Long countSaleProduct;
    private BigDecimal sumSaleProduct;
    private BigDecimal averageCheck;
    private Long countSaleProductWithDiscount;
    private BigDecimal sumSpreadAmount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Timestamp getSalePeriod() {
        return salePeriod;
    }

    public void setSalePeriod(Timestamp salePeriod) {
        this.salePeriod = salePeriod;
    }

    public Long getCountSaleProduct() {
        return countSaleProduct;
    }

    public void setCountSaleProduct(Long countSaleProduct) {
        this.countSaleProduct = countSaleProduct;
    }

    public BigDecimal getSumSaleProduct() {
        return sumSaleProduct;
    }

    public void setSumSaleProduct(BigDecimal sumSaleProduct) {
        this.sumSaleProduct = sumSaleProduct;
    }

    public BigDecimal getAverageCheck() {
        return averageCheck;
    }

    public void setAverageCheck(BigDecimal averageCheck) {
        this.averageCheck = averageCheck;
    }

    public Long getCountSaleProductWithDiscount() {
        return countSaleProductWithDiscount;
    }

    public void setCountSaleProductWithDiscount(Long countSaleProductWithDiscount) {
        this.countSaleProductWithDiscount = countSaleProductWithDiscount;
    }

    public BigDecimal getSumSpreadAmount() {
        return sumSpreadAmount;
    }

    public void setSumSpreadAmount(BigDecimal sumSpreadAmount) {
        this.sumSpreadAmount = sumSpreadAmount;
    }
}


