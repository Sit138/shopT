package model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discounted")
    private boolean discounted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)// TODO: Kirill почти все каскады ни к чему
    private Set<Discount> discounts = new HashSet<Discount>();

    public Product(){
    }

    public void addProductDiscont(Discount discount){
        discount.setProduct(this);
        getDiscounts().add(discount);// TODO: Kirill стандартного equals() hashCode() достаточно для корректной работы этого?
    }

}
