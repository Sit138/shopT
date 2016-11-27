package dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter @Setter
public class StatisticOnSaleDTO {

    private String productName;

    private Timestamp salePeriod;

    private Long countSaleProduct;

    private BigDecimal sumSaleProduct;

    private BigDecimal averageCheck;

    private Long countSaleProductWithDiscount;

    private BigDecimal sumSpreadAmount;

}


