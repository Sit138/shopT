package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import util.enums.SaleState;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Sale")
@Table(name = "sale")
@Getter @Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "positions")
    private int positions;// TODO: Kirill что это значит? как апдейтится?
    // логика увеличения этого числа не должна быть связана с добавлением в soldProducts?
    // :: == общее количество товаров (позиций) в корзине в момент покупки, изменил название

    @Column(name = "total_sum")
    private BigDecimal totalSum;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private SaleState state;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "sale_id")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<SoldProduct> soldProducts = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Sale(){}

}
