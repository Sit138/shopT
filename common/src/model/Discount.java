package model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.enums.DiscountType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount")
@Getter @Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    // TODO: Kirill discount и так далее и так далее 
    @Column(name = "discount_value")
    private double value;

    @Column(name = "discount_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "discount_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    // TODO: Kirill не надо комментарием с пояснением, сделай так чтоб не забыть никогда. Перечисление ++
    @Column(name = "add_type")
    @Enumerated(EnumType.STRING)
    private DiscountType addType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Discount() {
    }

    public Discount(double value, Date startDate, DiscountType discountType) {
        this.value = value;
        this.startDate = startDate;
        this.endDate = null;
        this.addType = discountType;
    }

}