package dto;

import model.enums.DiscountType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter @Setter
public class DiscountDTO {

    private byte value;

    private Timestamp startDate;

    private Timestamp endDate;

    private int productId;

    private String productName;

    private BigDecimal productPrice;

    private DiscountType addType;

}
