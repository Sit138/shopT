package dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class StatisticOnSaleDTO {

    @Getter @Setter
    private String productName;
    @Getter @Setter
    private Timestamp salePeriod;
    @Getter @Setter
    private Long countSaleProduct;
    @Getter @Setter
    private BigDecimal sumSaleProduct;
    @Getter @Setter
    private BigDecimal averageCheck;
    @Getter @Setter
    private Long countSaleProductWithDiscount;
    @Getter @Setter
    private BigDecimal sumSpreadAmount;

}


