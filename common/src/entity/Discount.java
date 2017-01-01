package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import util.enums.DiscountType;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "Discount")
@Table(name = "discount")
@Getter @Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    // TODO: Kirill discount и так далее и так далее 
    @Column(name = "value")
    private byte value;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    // TODO: Kirill не надо комментарием с пояснением, сделай так чтоб не забыть никогда. Перечисление ++
    @Column(name = "add_type")// TODO: Kirill type?
    @Enumerated(EnumType.STRING)
    private DiscountType addType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Discount() {
    }

    public Discount(byte value, Date startDate, DiscountType discountType) {
        this.value = value;
        this.startDate = startDate;
        this.endDate = null;
        this.addType = discountType;
    }

}
