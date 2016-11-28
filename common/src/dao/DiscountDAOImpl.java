package dao;

import dto.DiscountDTO;
import model.Discount;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import util.PaginationBuilder;
import model.enums.DiscountType;
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
        return Math.toIntExact((Long) getCurrentSession()
                .createCriteria(Discount.class)
                .setProjection(Projections.rowCount())
                .uniqueResult());
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

}
