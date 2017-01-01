package dto;

import lombok.Getter;
import lombok.Setter;
import entity.Buyer;

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

    public BuyerDTO(Buyer buyer){
        this.id = buyer.getId();
        this.name = buyer.getName();
        this.password = buyer.getPassword();
        this.enabled = buyer.isEnabled();
        this.balance = buyer.getBalance();
        this.registrationDate = buyer.getRegistrationDate();
    }

    public BuyerDTO() {

    }
}
