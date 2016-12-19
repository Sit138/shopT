package dao;

import dto.DiscountDTO;
import model.Discount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import util.Pagination;
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
    public void insertEndDateDiscount(Date endDateDiscount, int productId) {
        getCurrentSession().createQuery(
                "update Discount d set d.endDate = :endDateDiscount " +
                        "where d.endDate is null and d.product.id = :productId")
                .setParameter("endDateDiscount", endDateDiscount)
                .setParameter("productId", productId)
                .executeUpdate();
    }

    @Override
    public int numberItemsDiscountHistory() {// TODO: Kirill вот даже проджекшн называется count, но ты решил что твой термин лучше?  
        return Math.toIntExact((Long) getCurrentSession()
                .createCriteria(Discount.class)
                .setProjection(Projections.rowCount())
                .uniqueResult());
    }

    @Override
    public List<Integer> getIdWithoutDiscount() {// TODO: Kirill какие айди? снаружи это выглядит как репозиторийСкидок.дайАйдишкиБезСкидок
        return (List<Integer>) getCurrentSession()//ты ж вроде запихал в продукт набор скидок
                .createSQLQuery("SELECT p.id FROM product p " +
                        "WHERE p.id NOT IN (SELECT product_id FROM discount " +
                        "WHERE end_date ISNULL);")
                .list();
    }

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts(Pagination pagination) {// TODO: Kirill чем этот метод отличается от других что он именно select?
        return getCurrentSession()
                .createQuery("select d.value as value, d.startDate as startDate, d.endDate as endDate, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice, d.addType as addType " +
                        "from Discount d left outer join d.product p on p.id=d.product.id order by d.id desc")
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .setFirstResult(pagination.getNumberFirstSamplingElement())
                .setMaxResults(pagination.getNumberRowsOnPage())
                .list();
    }

    @Override
    public DiscountDTO getNowAutoDiscountProduct() {
        return (DiscountDTO) getCurrentSession()
                .createQuery("select d.value as value, d.startDate as startDate, d.endDate as endDate, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice, d.addType as addType " +
                        "from Discount d left outer join d.product p on p.id=d.product.id " +
                        "where d.endDate is null and d.addType = 'Auto'")// TODO: Kirill плохо так делать
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .uniqueResult();

    }

    @Override
    public byte getValueByProductId(int id) {
        return (byte) getCurrentSession()
                .createQuery("select d.value from Discount d " +
                        "where d.product.id = :id and d.endDate is null")
                .setParameter("id", id)
                .uniqueResult();
    }

}
