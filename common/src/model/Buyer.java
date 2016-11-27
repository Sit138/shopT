package model;

import dto.BuyerDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "buyer")
@Getter @Setter
public class Buyer {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer", cascade = CascadeType.ALL)
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
