package dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductDTO {

    @Getter @Setter
    private int id;

    @Size(min = 3, max = 15, message = "Поле \"Наименование\" продукта должно содержать от 3 до 15 знаков")
    @Getter @Setter
    private String name;

    @NotNull(message = "Поле \"Цена\" не может быть пустым!")
    @Range(min = 10, message = "Цена не может быть менее 10!")
    @Getter @Setter
    private BigDecimal price;

}
