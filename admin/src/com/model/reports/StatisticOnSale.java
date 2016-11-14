package com.model.reports;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "statistic_sale")
public class StatisticOnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "product_name")
    @Getter @Setter
    private String productName;

    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date saleDate;

    @Column(name = "count_sale_product")
    @Getter @Setter
    private int countSaleProduct;

    @Column(name = "sum_sale_product")
    @Getter @Setter
    private BigDecimal sumSaleProduct;

    @Column(name = "average_check")
    @Getter @Setter
    private BigDecimal averageCheck;

    @Column(name = "count_sale_product_with_discount")
    @Getter @Setter
    private int countSaleProductWithDiscount;

    @Column(name = "sum_spread_amount")
    @Getter @Setter
    private BigDecimal sumSpreadAmount;

}
