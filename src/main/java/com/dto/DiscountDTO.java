package com.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DiscountDTO {

    private double value;
    private Timestamp startDate;
    private Timestamp endDate;
    private BigDecimal productDiscountPrice;
    private BigDecimal discountPriceSpread;
    private int productId;
    private String productName;
    private BigDecimal productPrice;
    private int addType;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getAddType() {
        return addType;
    }

    public void setAddType(int addType) {
        this.addType = addType;
    }
}
