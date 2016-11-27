package model;

import dto.SoldProductDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sold_product")
@Getter @Setter
public class SoldProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private double discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "product_id")
    private int productId;

    public SoldProduct(){
    }

    public SoldProduct(SoldProductDTO soldProductDTO){
        this.amount = soldProductDTO.getAmount();
        this.price = soldProductDTO.getPrice();
        this.discount = soldProductDTO.getDiscount();
        this.name = soldProductDTO.getName();
        this.productId = soldProductDTO.getProductId();
    }

}
