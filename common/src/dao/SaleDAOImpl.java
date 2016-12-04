package dao;

import dto.SaleDTO;
import dto.SoldProductDTO;
import model.Sale;
import model.enums.SaleState;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleDAOImpl implements SaleDAO {

    private SessionFactory sessionFactory;

    public SaleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Sale sale) {
        getCurrentSession().save(sale);
    }

    @Override
    public List<SaleDTO> getByBuyerId(int buyerId) {
        return  getCurrentSession().createQuery("select s.id as id, s.date as date, " +
                "s.amount as amount, s.totalSum as totalSum, s.state as state " +
                "from Sale s where s.buyer.id = :buyerId")
                .setParameter("buyerId", buyerId)
                .setResultTransformer(Transformers.aliasToBean(SaleDTO.class))
                .list();
    }

    @Override
    public List<SoldProductDTO> getOrderInfo(int saleId) {
        return getCurrentSession()
                .createQuery("select s.name as name, s.amount as amount, " +
                "s.price as price, s.discount as discount " +
                "from SoldProduct s where s.sale.id = :saleId")
                .setParameter("saleId", saleId)
                .setResultTransformer(Transformers.aliasToBean(SoldProductDTO.class))
                .list();
    }

    @Override
    public List<SaleDTO> list() {
        return (List<SaleDTO>) getCurrentSession().
                createQuery("select s.id as id, s.date as date, " +
                        "s.amount as amount, s.totalSum as totalSum, s.state as state from Sale s")
                .setResultTransformer(Transformers.aliasToBean(SaleDTO.class))
                .list();
    }

    @Override
    public void updateState(int saleId, SaleState state) {
        getCurrentSession()
                .createQuery("update Sale s set s.state = :state where s.id = :id")
                .setParameter("id", saleId)
                .setParameter("state", state)
                .executeUpdate();
    }
}
