package com.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TotalSaleReport {
    private Timestamp saleDate;
    private Long saleCount;
    private BigDecimal saleSum;
    private BigDecimal saleAverageCheck;
    private Long countSaleProductByDiscount;
    private BigDecimal discountSum;

    public String getSaleDateStringFormat(){
        Timestamp date = getSaleDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

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

    public BigDecimal getSaleAverageCheck() {
        return saleAverageCheck;
    }

    public void setSaleAverageCheck(BigDecimal saleAverageCheck) {
        this.saleAverageCheck = saleAverageCheck;
    }

    public Long getCountSaleProductByDiscount() {
        return countSaleProductByDiscount;
    }

    public void setCountSaleProductByDiscount(Long countSaleProductByDiscount) {
        this.countSaleProductByDiscount = countSaleProductByDiscount;
    }

    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(BigDecimal discountSum) {
        this.discountSum = discountSum;
    }
}
