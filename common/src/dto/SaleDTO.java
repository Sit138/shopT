package dto;

import lombok.Getter;
import lombok.Setter;
import util.enums.SaleState;
import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class SaleDTO {

    private int id;

    private Date date;

    private int amount;

    private BigDecimal totalSum;

    private SaleState state;

}
