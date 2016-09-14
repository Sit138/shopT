package com.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductDTO {

    private int id;

    @Size(min = 3, max = 15, message = "Поле \"Наименование\" продукта должно содержать от 3 до 15 знаков")
    private String name;

    @NotNull(message = "Поле \"Цена\" не может быть пустым!")
    @Range(min = 10, message = "Цена не может быть менее 10!")
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
