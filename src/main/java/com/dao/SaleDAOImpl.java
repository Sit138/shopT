package com.dao;

import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<SaleProductInRangeDetailed> saleListInRangePagination(int pageId, int maxResults) {
        List<SaleProductInRangeDetailed> saleInRangeDetailedList = getCurrentSession()
                .createSQLQuery("SELECT p.id AS productId, p.product_name AS productName, date_trunc('hour', s.sale_date) AS saleDate, " +
                        "COUNT(s.product_id) AS saleCount, SUM(s.sale_amount) AS saleAmount " +
                        "FROM sale s LEFT OUTER JOIN product p ON p.id = s.product_id " +
                        "GROUP BY date_trunc('hour', s.sale_date), p.id " +
                        "ORDER BY date_trunc('hour', s.sale_date) ASC, p.id ASC")
                .addScalar("productId", IntegerType.INSTANCE)
                .addScalar("productName", StringType.INSTANCE)
                .addScalar("saleDate", TimestampType.INSTANCE)
                .addScalar("saleCount", LongType.INSTANCE)
                .addScalar("saleAmount", BigDecimalType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(SaleProductInRangeDetailed.class))
                .setFirstResult(pageId)
                .setMaxResults(maxResults)
                .list();
        return saleInRangeDetailedList;
    }

    @Override
    public List<TotalSaleReport> totalSaleReport() {

        List<TotalSaleReport> totalSaleReportList = getCurrentSession()
                .createSQLQuery("SELECT date_trunc('hour', s.sale_date) AS saleDate, COUNT(s.id) AS saleCount, " +
                                "SUM(s.sale_amount) AS saleSum, round(sum(s.sale_amount)/count(s.id)) AS saleAverageCheck, " +
                                "COUNT(d.id) AS countSaleProductByDiscount, SUM(d.discount_price_spread) AS discountSum " +
                                "FROM sale s " +
                                "LEFT OUTER JOIN discount d ON date_trunc('hour', s.sale_date)=date_trunc('hour', d.discount_date) " +
                                                           "AND s.product_id=d.product_id\n" +
                                "GROUP BY date_trunc('hour', s.sale_date) " +
                                "ORDER BY date_trunc('hour', s.sale_date)")
                .addScalar("saleDate", TimestampType.INSTANCE)
                .addScalar("saleCount", LongType.INSTANCE)
                .addScalar("saleSum", BigDecimalType.INSTANCE)
                .addScalar("saleAverageCheck", BigDecimalType.INSTANCE)
                .addScalar("countSaleProductByDiscount", LongType.INSTANCE)
                .addScalar("discountSum", BigDecimalType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(TotalSaleReport.class)).list();

        return totalSaleReportList;
    }

    @Override
    public int numberItemsTheSaleRangeReport() {
        int num = (Integer) getCurrentSession()
                .createSQLQuery("SELECT  count(result.id) AS num FROM " +
                        "(SELECT p.id AS id FROM sale s LEFT OUTER JOIN product p ON p.id = s.product_id " +
                        "GROUP BY date_trunc('hour', s.sale_date), p.id) AS result")
                .addScalar("num", IntegerType.INSTANCE)
                .uniqueResult();
        return num;
    }

}
