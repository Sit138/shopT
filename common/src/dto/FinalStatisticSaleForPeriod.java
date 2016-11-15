package dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

public class FinalStatisticSaleForPeriod {

    @Getter @Setter
    private Long sumCountSaleProduct;
    @Getter @Setter
    private BigDecimal fullSumSaleProduct;
    @Getter @Setter
    private Long sumCountSaleProductWithDiscount;
    @Getter @Setter
    private BigDecimal fullSumSpreadAmount;
    @Getter @Setter
    private BigDecimal averageCheckOnPeriod;

}
