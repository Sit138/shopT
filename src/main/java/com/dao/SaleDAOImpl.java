package com.dao;

import com.dto.FinalStatisticSaleForPeriod;
import com.dto.StatisticOnSaleDTO;
import com.dto.util.FilterStatisticSale;
import com.dto.util.PaginationBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SaleDAOImpl implements SaleDAO {

    private SessionFactory sessionFactory;

    public SaleDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<StatisticOnSaleDTO> getStatisticOnSale(FilterStatisticSale paginationBuilder) {

        List<StatisticOnSaleDTO> totalSaleReportList = getCurrentSession()
                .createSQLQuery("SELECT product_name AS productName, " +
                                        "sale_date AS salePeriod, " +
                                        "count_sale_product AS countSaleProduct, " +
                                        "sum_sale_product AS sumSaleProduct, " +
                                        "average_check AS averageCheck, " +
                                        "count_sale_product_with_discount AS countSaleProductWithDiscount, " +
                                        "sum_spread_amount AS sumSpreadAmount " +
                                "FROM statistic_sale s " +
                                "WHERE s.sale_date >= :fromDate AND s.sale_date < :toDate " +
                                "ORDER BY date_trunc('hour', sale_date), s.product_name DESC")
                .addScalar("productName", StringType.INSTANCE)
                .addScalar("salePeriod", TimestampType.INSTANCE)
                .addScalar("countSaleProduct", LongType.INSTANCE)
                .addScalar("sumSaleProduct", BigDecimalType.INSTANCE)
                .addScalar("averageCheck", BigDecimalType.INSTANCE)
                .addScalar("countSaleProductWithDiscount", LongType.INSTANCE)
                .addScalar("sumSpreadAmount", BigDecimalType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(StatisticOnSaleDTO.class))
                .setParameter("fromDate", paginationBuilder.getFromDate())
                .setParameter("toDate", paginationBuilder.getToDate())
                .setFirstResult(paginationBuilder.getNumberFirstSamplingElement())
                .setMaxResults(paginationBuilder.getNumberRowsOnPage())
                .list();
        return totalSaleReportList;
    }

    @Override
    public FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod(Date fromDate, Date toDate) {
        return (FinalStatisticSaleForPeriod) getCurrentSession()
                .createSQLQuery("SELECT sum(s.count_sale_product) AS sumCountSaleProduct, " +
                                        "sum(s.sum_sale_product) AS fullSumSaleProduct, " +
                                        "sum(s.count_sale_product_with_discount) AS sumCountSaleProductWithDiscount, " +
                                        "sum(s.sum_spread_amount) AS fullSumSpreadAmount, " +
                                        "round(sum(s.sum_sale_product)/sum(s.count_sale_product)) AS averageCheckOnPeriod " +
                                "FROM statistic_sale s " +
                                "WHERE s.sale_date >= :fromDate AND s.sale_date < :toDate"
                )
                .addScalar("sumCountSaleProduct", LongType.INSTANCE)
                .addScalar("fullSumSaleProduct", BigDecimalType.INSTANCE)
                .addScalar("sumCountSaleProductWithDiscount", LongType.INSTANCE)
                .addScalar("fullSumSpreadAmount", BigDecimalType.INSTANCE)
                .addScalar("averageCheckOnPeriod", BigDecimalType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(FinalStatisticSaleForPeriod.class))
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .uniqueResult();
    }


    @Override
    public int numberItemsTheSaleRangeReport(Date fromDate, Date toDate) {
        return (Integer) getCurrentSession()
                .createSQLQuery("SELECT count(st.id) AS countRows " +
                                "FROM statistic_sale st " +
                                "WHERE st.sale_date >= :fromDate AND st.sale_date < :toDate")
                .addScalar("countRows", IntegerType.INSTANCE)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .uniqueResult();
    }

    @Override
    public void aggregateSalesOfProductInTheLastHour() {
        getCurrentSession()
                .createSQLQuery("INSERT INTO statistic_sale (" +
                                                            "product_name, " +
                                                            "sale_date, " +
                                                            "count_sale_product, " +
                                                            "sum_sale_product, " +
                                                            "average_check, " +
                                                            "count_sale_product_with_discount, " +
                                                            "sum_spread_amount) " +
                                "SELECT p.product_name, " +
                                        "date_trunc('hour', sale_on_product.sale_date), " +
                                        "count(p.id), " +
                                        "sum(sale_on_product.sale_amount), " +
                                        "round(sum(sale_on_product.sale_amount)/count(sale_on_product.product_id)), " +
                                        "count(CASE WHEN sale_on_product.spread_price_amount > 0 THEN 1 END) AS countWithDisc, " +
                                        "sum(sale_on_product.spread_price_amount) " +
                                "FROM ( " +
                                        "SELECT s.product_id, " +
                                        "s.sale_amount, " +
                                        "s.sale_date, " +
                                        "s.spread_price_amount " +
                                        "FROM sale s " +
                                ") sale_on_product " +
                                "left join product p on p.id=sale_on_product.product_id " +
                                "WHERE date_trunc('hour', sale_on_product.sale_date) BETWEEN " +
                                    "CASE WHEN (SELECT count(statistic_sale.sale_date) FROM statistic_sale) <> 0 " +
                        "                       THEN (SELECT date_trunc('hour', (SELECT max(statistic_sale.sale_date) FROM statistic_sale) + INTERVAL '1 hour')) " +
                                          "WHEN (SELECT count(statistic_sale.sale_date) FROM statistic_sale) = 0 " +
                        "                       THEN (select TIMESTAMP '2015-01-01') " +
                                    "END " +
                                "AND date_trunc('hour', current_timestamp) " +
                                "group by p.id, date_trunc('hour', sale_on_product.sale_date)")
                .executeUpdate();
    }

}
