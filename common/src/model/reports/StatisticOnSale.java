package model.reports;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "statistic_sale")
@Getter @Setter
public class StatisticOnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;

    @Column(name = "count_sale_product")
    private int countSaleProduct;

    @Column(name = "sum_sale_product")
    private BigDecimal sumSaleProduct;

    @Column(name = "average_check")
    private BigDecimal averageCheck;

    @Column(name = "count_sale_product_with_discount")
    private int countSaleProductWithDiscount;

    @Column(name = "sum_spread_amount")
    private BigDecimal sumSpreadAmount;

}
