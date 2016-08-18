package com.dto;

import com.model.Product;
import com.model.Sale;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO extends Product {
    private int id;
    private String productName;
    private BigDecimal productPrice;
    private Set<Sale> sales = new HashSet<Sale>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

}
