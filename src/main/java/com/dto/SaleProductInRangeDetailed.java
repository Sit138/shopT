package com.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SaleProductInRangeDetailed {
    private int productId;
    private String productName;
    private Timestamp saleDate;
    private Long saleCount;
    private BigDecimal saleAmount;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getSaleDateStringFormat(){
        Timestamp date = getSaleDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }
}
