package dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class FinalStatisticSaleForPeriod {

    private Long sumCountSaleProduct;

    private BigDecimal fullSumSaleProduct;

    private Long sumCountSaleProductWithDiscount;

    private BigDecimal fullSumSpreadAmount;

    private BigDecimal averageCheckOnPeriod;

}
