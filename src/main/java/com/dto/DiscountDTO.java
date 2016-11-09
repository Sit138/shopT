package com.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DiscountDTO {

    @Getter @Setter
    private double value;
    @Getter @Setter
    private Timestamp startDate;
    @Getter @Setter
    private Timestamp endDate;
    @Getter @Setter
    private int productId;
    @Getter @Setter
    private String productName;
    @Getter @Setter
    private BigDecimal productPrice;
    @Getter @Setter
    private int addType;

}
