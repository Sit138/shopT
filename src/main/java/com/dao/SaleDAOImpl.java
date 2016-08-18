package com.dao;

import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleDAOImpl implements SaleDAO {

    @Autowired
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

    @Override
    public List<SaleProductInRangeDetailed> saleListInRange() {

        List<SaleProductInRangeDetailed> saleInRangeDetailedList = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT p.id AS productId, p.product_name AS productName, date_trunc('hour', s.sale_date) AS saleDate, count(s.product_id) AS saleCount, sum(s.sale_amount) AS saleAmount\n" +
                        "FROM sale s LEFT OUTER JOIN product p ON p.id = s.product_id \n" +
                        "GROUP BY date_trunc('hour', s.sale_date), p.id \n" +
                        "ORDER BY date_trunc('hour', s.sale_date) ASC")
                .addScalar("productId", new IntegerType())
                .addScalar("productName", new StringType())
                .addScalar("saleDate", new TimestampType())
                .addScalar("saleCount", new LongType())
                .addScalar("saleAmount", new BigDecimalType())
                .setResultTransformer(Transformers.aliasToBean(SaleProductInRangeDetailed.class)).list();
        return saleInRangeDetailedList;
    }

    @Override
    public List<TotalSaleReport> totalSaleReport() {

        List<TotalSaleReport> totalSaleReportList = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT date_trunc('hour', s.sale_date) AS saleDate, COUNT(s.id) AS saleCount,\n" +
                                "SUM(s.sale_amount) AS saleSum, round(sum(s.sale_amount)/count(s.id)) AS saleAverageCheck, \n" +
                                "COUNT(d.id) AS countSaleProductByDiscount, SUM(d.discount_price_spread) AS discountSum\n" +
                                "FROM sale s\n" +
                                "LEFT OUTER JOIN discount d ON date_trunc('hour', s.sale_date)=date_trunc('hour', d.discount_date) \n" +
                                                           "AND s.product_id=d.product_id\n" +
                                "GROUP BY date_trunc('hour', s.sale_date)\n" +
                                "ORDER BY date_trunc('hour', s.sale_date)")
                .addScalar("saleDate", new TimestampType())
                .addScalar("saleCount", new LongType())
                .addScalar("saleSum", new BigDecimalType())
                .addScalar("saleAverageCheck", new BigDecimalType())
                .addScalar("countSaleProductByDiscount", new LongType())
                .addScalar("discountSum", new BigDecimalType())
                .setResultTransformer(Transformers.aliasToBean(TotalSaleReport.class)).list();

        return totalSaleReportList;
    }

}
