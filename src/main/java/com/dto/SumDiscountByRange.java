package com.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SumDiscountByRange {
    private BigDecimal sumDiscount;
    private Long countSaleProductByDiscount;
    private int productId;
    private Timestamp saleDiscountDate;

    public BigDecimal getSumDiscount() {
        return sumDiscount;
    }

    public void setSumDiscount(BigDecimal sumDiscount) {
        this.sumDiscount = sumDiscount;
    }

    public Long getCountSaleProductByDiscount() {
        return countSaleProductByDiscount;
    }

    public void setCountSaleProductByDiscount(Long countSaleProductByDiscount) {
        this.countSaleProductByDiscount = countSaleProductByDiscount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Timestamp getSaleDiscountDate() {
        return saleDiscountDate;
    }

    public void setSaleDiscountDate(Timestamp saleDiscountDate) {
        this.saleDiscountDate = saleDiscountDate;
    }
}
