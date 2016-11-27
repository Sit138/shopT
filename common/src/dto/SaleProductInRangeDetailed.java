package dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter @Setter
public class SaleProductInRangeDetailed {

    private int productId;

    private String productName;

    private Timestamp saleDate;

    private Long saleCount;

    private BigDecimal saleAmount;

}
