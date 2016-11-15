package dto;

import model.DiscountType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private DiscountType addType;

}
