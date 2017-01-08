package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)// TODO: Kirill почти все каскады ни к чему
    private Set<Discount> discounts = new HashSet<>(); //:: здесь используется, т.к. сохранение происходит через сохранения продукта
                                                        //соответственно каскад нужен. если так неприемлимо, то можно конечно изменить
                                                        //:: оставил в аналогичных случаях
    public Product(){
    }

    public void addProductDiscount(Discount discount){
        discount.setProduct(this);
        getDiscounts().add(discount);// TODO: Kirill стандартного equals() hashCode() достаточно для корректной работы этого?
    }


}
