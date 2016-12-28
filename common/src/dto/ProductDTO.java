package dto;

import lombok.Getter;
import lombok.Setter;
import model.Product;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter @Setter
public class ProductDTO {

    private int id;

    @Size(min = 3, max = 15, message = "Поле \"Наименование\" продукта должно содержать от 3 до 15 знаков")
    private String name;

    @NotNull(message = "Поле \"Цена\" не может быть пустым!")
    @Range(min = 10, message = "Цена не может быть менее 10!")
    private BigDecimal price;

    private boolean discounted;

    public ProductDTO(){}

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.discounted = product.isDiscounted();
    }

}
