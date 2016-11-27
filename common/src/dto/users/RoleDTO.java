package dto.users;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Size;

@Getter @Setter
public class RoleDTO {

    private int id;

    @Size(min = 3, max = 15, message = "Поле \"Имя роли\" должно содержать от 3 до 15 знаков")
    private String nameRole;

}
