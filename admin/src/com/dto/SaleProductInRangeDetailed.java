package com.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class SaleProductInRangeDetailed {

    @Getter @Setter
    private int productId;
    @Getter @Setter
    private String productName;
    @Getter @Setter
    private Timestamp saleDate;
    @Getter @Setter
    private Long saleCount;
    @Getter @Setter
    private BigDecimal saleAmount;

}
