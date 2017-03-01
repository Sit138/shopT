package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class DialogDTO {

    private int dialogId;

    private int lastMessageId;

    private int lastSenderId;

    private String lastSenderName;

    private String lastText;

    private Date messageDate;

    private boolean enabled;

    private boolean readAll;

    private int currentUserId;

    private String interlocutorName;

}
