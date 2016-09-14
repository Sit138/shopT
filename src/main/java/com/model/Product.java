package com.model;

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
    private int id;

    @Column(name = "product_name")
    //@Size(min = 3, max = 15, message = "Поле наименование продукта должно содержать от 3 до 15 знаков")
    private String name;

    @Column(name = "product_price")
    //@NotNull(message = "Empty")
    //@Range(min = 10, message = "Цена не может быть менее 10")
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Discount> discounts = new HashSet<Discount>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Sale> sales = new HashSet<Sale>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void addProductDiscont(Discount discount){
        discount.setProduct(this);
        getDiscounts().add(discount);
    }

    public void addProductSale(Sale sale){
        sale.setProduct(this);
        getSales().add(sale);
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
