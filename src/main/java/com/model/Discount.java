package com.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // TODO: Kirill не надо комментарием с пояснением, сделай так чтоб не забыть никогда. Перечисление 
    @Column(name = "add_type")
    private int addType;//1 - auto, 2 - manual

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Discount(){
    }

    public Discount(double value, Date startDate, int addType){
        this.value = value;
        this.startDate = startDate;
        this.endDate = null;
        this.addType = addType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDiscountValue() {
        return value;
    }

    public void setDiscountValue(double discount_value) {
        this.value = discount_value;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date discount_date) {
        this.startDate = discount_date;
    }

    public int getId() {
        return id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAddType() {
        return addType;
    }

    public void setAddType(int addType) {
        this.addType = addType;
    }
}
