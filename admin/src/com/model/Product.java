package com.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    public Product(){
    }

    public Product(int id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "product_name")
    @Getter @Setter
    private String name;

    @Column(name = "product_price")
    @Getter @Setter
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    @Getter @Setter
    private Set<Discount> discounts = new HashSet<Discount>();

    // TODO: Kirill чудна'я связь какая-то. У продукта список его продаж... ок, а как тогда мне сделать покупку двух разных товаров? за один раз
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    @Getter @Setter
    private Set<Sale> sales = new HashSet<Sale>();

    public void addProductDiscont(Discount discount){
        discount.setProduct(this);
        getDiscounts().add(discount);
    }

    public void addProductSale(Sale sale){
        sale.setProduct(this);
        getSales().add(sale);
    }

}
