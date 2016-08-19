package com.dto;

import com.model.Product;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductDTO extends Product {

    private int id;

    @Size(min = 3, max = 15, message = "Поле наименование продукта должно содержать от 3 до 15 знаков")
    private String productName;

    @NotNull(message = "Empty")
    @Range(min = 10, message = "Цена не может быть менее 10")
    private BigDecimal productPrice;

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


}
