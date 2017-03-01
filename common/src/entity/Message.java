package entity;

import dto.MessageDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "Message")
@Table(name = "message")
@Getter @Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    public Message(MessageDTO messageDTO){
        this.senderId = messageDTO.getSenderId();
        this.text = messageDTO.getText();
        this.date = new Date();
    }

}
