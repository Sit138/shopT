package entity;

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
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Product")
@Table(name = "product")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "name", length = 15)
    @Length(min = 3, max = 15)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discounted")
    private boolean discounted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")// TODO: Kirill почти все каскады ни к чему
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Discount> discounts = new HashSet<>();

    public Product(){
    }

    public void addProductDiscount(Discount discount){
        discount.setProduct(this);
        getDiscounts().add(discount);// TODO: Kirill стандартного equals() hashCode() достаточно для корректной работы этого?
    }


}
