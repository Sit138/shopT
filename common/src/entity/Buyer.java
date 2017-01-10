package entity;

import dto.BuyerDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Buyer")
@Table(name = "buyer")
@Getter @Setter
public class Buyer {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, length = 15)
    @Length(min = 3, max = 15)
    private String name;// TODO: Kirill разве у тебя имя уникально для buyer?  если нет, то приложение дырявее некуда сейчас.
                            //Поправил

    @Column(name = "password", length = 15)
    @Length(min = 6, max = 15)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
    @Cascade(CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Sale> sales = new HashSet<>();

    public Buyer(){}

    public Buyer(BuyerDTO buyerDTO){
        this.name = buyerDTO.getName();
        this.password = buyerDTO.getPassword();
        this.enabled = true;
        this.balance = BigDecimal.ZERO;
        this.registrationDate = new Date();
    }

}
