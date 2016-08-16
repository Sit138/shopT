package com.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TotalSaleReport {
    private Timestamp saleDate;
    private Long saleCount;
    private BigDecimal saleSum;
    private BigDecimal averageCheck;

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public BigDecimal getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(BigDecimal saleSum) {
        this.saleSum = saleSum;
    }

    public BigDecimal getAverageCheck() {
        return averageCheck;
    }

    public void setAverageCheck(BigDecimal averageCheck) {
        this.averageCheck = averageCheck;
    }
}
