package com.dao;

import com.dto.DiscountDTO;
import com.dto.util.PaginationBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import java.util.Date;
import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {

    private SessionFactory sessionFactory;

    public DiscountDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void insertEndDateDiscount(int addTypeDiscount, Date endDateDiscount, int productId) {
        getCurrentSession().createSQLQuery(
                "UPDATE discount SET discount_end_date = :endDateDiscount " +
                        "WHERE discount_end_date IS NULL AND add_type = :addTypeDiscount AND product_id = :productId"
        )
                .setParameter("endDateDiscount", endDateDiscount)
                .setParameter("addTypeDiscount", addTypeDiscount)
                .setParameter("productId", productId)
                .executeUpdate();
    }

    @Override
    public int numberItemsDiscountHistory() {
        return (Integer) getCurrentSession()
                .createSQLQuery("SELECT count(d.id) AS countRows FROM discount d")
                .addScalar("countRows", IntegerType.INSTANCE)
                .uniqueResult();
    }

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder) {
        return getCurrentSession()
                .createQuery("select d.value as value, d.startDate as startDate, d.endDate as endDate, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice, d.addType as addType " +
                        "from Discount d left outer join d.product p on p.id=d.product.id order by d.id desc")
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .setFirstResult(paginationBuilder.getNumberFirstSamplingElement())
                .setMaxResults(paginationBuilder.getNumberRowsOnPage())
                .list();
    }

    @Override
    public DiscountDTO getNowDiscountProduct() {

        DiscountDTO discountProductNowDTO = (DiscountDTO) getCurrentSession()
                .createSQLQuery("SELECT d.discount_value AS value, d.discount_start_date AS startDate, d.discount_end_date AS endDate, " +
                        "p.id AS productId, p.product_name AS productName, p.product_price AS productPrice, d.add_type AS addType " +
                        "FROM discount d LEFT OUTER JOIN product p ON p.id=d.product_id " +
                        "WHERE d.discount_end_date is null")
                .addScalar("value", DoubleType.INSTANCE)
                .addScalar("startDate", TimestampType.INSTANCE)
                .addScalar("endDate", TimestampType.INSTANCE)
                .addScalar("productId", IntegerType.INSTANCE)
                .addScalar("productName", StringType.INSTANCE)
                .addScalar("productPrice", BigDecimalType.INSTANCE)
                .addScalar("addType", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class)).uniqueResult();
        if (discountProductNowDTO != null){
            return discountProductNowDTO;
        }
        return null;
    }

}
