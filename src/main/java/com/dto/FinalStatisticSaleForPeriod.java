package com.dto;

import java.math.BigDecimal;

public class FinalStatisticSaleForPeriod {

    private Long sumCountSaleProduct;
    private BigDecimal fullSumSaleProduct;
    private Long sumCountSaleProductWithDiscount;
    private BigDecimal fullSumSpreadAmount;
    private BigDecimal averageCheckOnPeriod;

    public Long getSumCountSaleProduct() {
        return sumCountSaleProduct;
    }

    public void setSumCountSaleProduct(Long sumCountSaleProduct) {
        this.sumCountSaleProduct = sumCountSaleProduct;
    }

    public BigDecimal getFullSumSaleProduct() {
        return fullSumSaleProduct;
    }

    public void setFullSumSaleProduct(BigDecimal fullSumSaleProduct) {
        this.fullSumSaleProduct = fullSumSaleProduct;
    }

    public Long getSumCountSaleProductWithDiscount() {
        return sumCountSaleProductWithDiscount;
    }

    public void setSumCountSaleProductWithDiscount(Long sumCountSaleProductWithDiscount) {
        this.sumCountSaleProductWithDiscount = sumCountSaleProductWithDiscount;
    }

    public BigDecimal getFullSumSpreadAmount() {
        return fullSumSpreadAmount;
    }

    public void setFullSumSpreadAmount(BigDecimal fullSumSpreadAmount) {
        this.fullSumSpreadAmount = fullSumSpreadAmount;
    }

    public BigDecimal getAverageCheckOnPeriod() {
        return averageCheckOnPeriod;
    }

    public void setAverageCheckOnPeriod(BigDecimal averageCheckOnPeriod) {
        this.averageCheckOnPeriod = averageCheckOnPeriod;
    }
}
