package dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class MessageDTO {

    private int id;

    private int senderId;

    @Size(min = 1, max = 255, message = "Поле \"Текст сообщения\" должно содержать от 1 до 255 знаков")
    private String text;

    private Date date;

}
