package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Dialog")
@Table(name = "dialog")
@Getter @Setter
@NoArgsConstructor
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dialog")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.dialog")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<BuyerDialog> buyerDialogs = new HashSet<>();

    public void addMessage(Message message){
        message.setDialog(this);
        getMessages().add(message);
    }

}
