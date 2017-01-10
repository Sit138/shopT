package dto;

import lombok.Getter;
import lombok.Setter;
import util.enums.DiscountType;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter @Setter
public class DiscountDTO {

    private byte value;

    private Timestamp startAt;

    private Timestamp endAt;

    private int productId;

    private String productName;

    private BigDecimal productPrice;

    private DiscountType type;

}
