package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import util.enums.DiscountType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    @Column(name = "start_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startAt;

    @Column(name = "end_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endAt;

    @Column(name = "type")// TODO: Kirill type?::Скидка с типом добавления - AUTO, MANUAL
    @Enumerated(EnumType.STRING)
    private DiscountType type;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Discount() {
    }

    public Discount(byte value, Date startAt, DiscountType discountType) {
        this.value = value;
        this.startAt = startAt;
        this.endAt = null;
        this.type = discountType;
    }

}
