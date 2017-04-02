package dao;

import dto.DiscountDTO;
import entity.Discount;
import org.springframework.stereotype.Repository;
import util.enums.DiscountType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import util.Pagination;
import java.util.Date;
import java.util.List;

@Repository(value = "discountDAO")
public class DiscountDAOImpl extends GeneralDAOImpl<Discount> implements DiscountDAO {

    /*private SessionFactory sessionFactory;

    public DiscountDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }*/

    @Override
    public void insertEndDateDiscount(Date endDateDiscount, int productId) {
        getSession().createQuery(
                "update Discount d set d.endAt = :endDateDiscount " +
                        "where d.endAt is null and d.product.id = :productId")
                .setParameter("endDateDiscount", endDateDiscount)
                .setParameter("productId", productId)
                .executeUpdate();
    }

    @Override
    public int countItemsDiscountHistory() {// TODO: Kirill вот даже проджекшн называется count, но ты решил что твой термин лучше?
        return Math.toIntExact((Long) getSession()
                .createCriteria(Discount.class)
                .setProjection(Projections.rowCount())
                .uniqueResult());
    }

    @Override
    public List<DiscountDTO> getHistoryProductDiscounts(Date dateFrom, Date dateTo, Pagination pagination) {// TODO: Kirill чем этот метод отличается от других что он именно select?:: :D
        return getSession()
                .createQuery("select d.value as value, d.startAt as startAt, d.endAt as endAt, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice, d.type as type " +
                        "from Discount d left outer join d.product p on p.id = d.product.id " +
                        "where d.startAt between :dateFrom and :dateTo " +
                        "order by d.id desc")
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .setFirstResult(pagination.getNumberFirstSamplingElement())
                .setMaxResults(pagination.getNumberRowsOnPage())
                .list();
    }

    @Override
    public DiscountDTO getNowAutoDiscountProduct() {
        return (DiscountDTO) getSession()
                .createQuery("select d.value as value, d.startAt as startAt, d.endAt as endAt, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice, d.type as type " +
                        "from Discount d left outer join d.product p on p.id=d.product.id " +
                        "where d.endAt is null and d.type = :typeDisc")// TODO: Kirill плохо так делать::исправил
                .setParameter("typeDisc", DiscountType.Auto)
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .uniqueResult();

    }

    @Override
    public byte getValueByProductId(int id) {
        return (byte) getSession()
                .createQuery("select d.value from Discount d " +
                        "where d.product.id = :id and d.endAt is null")
                .setParameter("id", id)
                .uniqueResult();
    }

}
