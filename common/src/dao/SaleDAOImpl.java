package dao;

import dto.SaleDTO;
import dto.SoldProductDTO;
import entity.Sale;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import util.Pagination;
import util.enums.SaleState;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository(value = "saleDAO")
public class SaleDAOImpl extends GeneralDAOImpl<Sale> implements SaleDAO {

    /*private SessionFactory sessionFactory;

    public SaleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Sale sale) {
        getSession().save(sale);
    }*/

    @Override
    public List<SaleDTO> getByBuyerId(int buyerId) {
        return  getSession().createQuery("select s.id as id, s.date as date, " +
                "s.positions as positions, s.totalSum as totalSum, s.state as state " +
                "from Sale s where s.buyer.id = :buyerId")
                .setParameter("buyerId", buyerId)
                .setResultTransformer(Transformers.aliasToBean(SaleDTO.class))
                .list();
    }

    @Override
    public List<SoldProductDTO> getOrderInfo(int saleId) {
        return getSession()
                .createQuery("select s.name as name, s.amount as amount, " +
                "s.price as price, s.discount as discount " +
                "from SoldProduct s where s.sale.id = :saleId")
                .setParameter("saleId", saleId)
                .setResultTransformer(Transformers.aliasToBean(SoldProductDTO.class))
                .list();
    }

    @Override
    public List<SaleDTO> list(Pagination pagination) {
        return (List<SaleDTO>) getSession().
                createQuery("select s.id as id, s.date as date, " +
                        "s.positions as positions, s.totalSum as totalSum, s.state as state from Sale s")
                .setResultTransformer(Transformers.aliasToBean(SaleDTO.class))
                .setFirstResult(pagination.getNumberFirstSamplingElement())
                .setMaxResults(pagination.getNumberRowsOnPage())
                .list();
    }

    @Override
    public void updateState(int saleId, SaleState state) {
        getSession()
                .createQuery("update Sale s set s.state = :state where s.id = :id")
                .setParameter("id", saleId)
                .setParameter("state", state)
                .executeUpdate();
    }

    @Override
    public int countItemsSaleHistory() {
        return Math.toIntExact((Long) getSession()
                .createCriteria(Sale.class)
                .setProjection(Projections.rowCount())
                .uniqueResult());
    }
}
