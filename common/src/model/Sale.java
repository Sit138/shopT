package model;


import dto.Basket;
import dto.BuyerDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.enums.SaleState;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sale")
@Getter @Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private int amount;

    @Column(name = "total_sum")
    private BigDecimal totalSum;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private SaleState state;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<SoldProduct> soldProducts = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Sale(){}

    public Sale(Buyer buyer, Basket basket){
        this.date = new Date();
        this.amount = basket.getCountProducts();
        this.totalSum = basket.getCost();
        this.state = SaleState.SENT;
        this.soldProducts = basket.getConversionToSoldProduct();
        this.buyer = buyer;
    }

}
