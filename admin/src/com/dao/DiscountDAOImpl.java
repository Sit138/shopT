package com.dao;

import com.dto.DiscountDTO;
import com.dto.util.PaginationBuilder;
import com.model.DiscountType;
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
    public void insertEndDateDiscount(DiscountType discountType, Date endDateDiscount, int productId) {
        getCurrentSession().createQuery(
                "update Discount d set d.endDate = :endDateDiscount " +
                        "where d.endDate is null and d.addType = :discountType and d.product.id = :productId"
        )
                .setParameter("endDateDiscount", endDateDiscount)
                .setParameter("discountType", discountType)
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

        return (DiscountDTO) getCurrentSession()
                .createQuery("select d.value as value, d.startDate as startDate, d.endDate as endDate, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice, d.addType as addType " +
                        "from Discount d left outer join d.product p on p.id=d.product.id " +
                        "where d.endDate is null")
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .uniqueResult();

    }

    /*@Override
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
                .addScalar("addType", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class)).uniqueResult();
        if (discountProductNowDTO != null){
            return discountProductNowDTO;
        }
        return null;
    }*/

}
