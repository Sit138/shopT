package dto.users;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Size;

@Getter @Setter
public class UserDTO {

    private int id;

    // TODO: Kirill отражение этих ограниченией в базе есть?
    @Size(min = 3, max = 15, message = "Поле \"Имя пользователя\" должно содержать от 3 до 15 знаков")
    private String userName;

    @Size(min = 6, max = 15, message = "Поле \"Пароль\" должно содержать от 6 до 15 знаков")
    private String password;

    private String nameRole;

    private boolean enabled;

}
