package dto;

import lombok.Getter;
import lombok.Setter;
import model.Buyer;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class BuyerDTO {

    private int id;

    @Size(min = 3, max = 15, message = "Поле \"Имя пользователя\" должно содержать от 5 до 15 знаков")
    private String name;

    @Size(min = 6, max = 15, message = "Поле \"Пароль\" должно содержать от 6 до 15 знаков")
    private String password;

    private boolean enabled;

    private BigDecimal balance;

    private Date registrationDate;

}
