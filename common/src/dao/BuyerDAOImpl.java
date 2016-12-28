package dao;

import model.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

// TODO: Kirill почему ты используешь везде имя вместо айди?
@Repository
public class BuyerDAOImpl implements BuyerDAO {

    private SessionFactory sessionFactory;

    public BuyerDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }// TODO: Kirill getSession()

    @Override
    public void save(Buyer buyer) {
        getSession().save(buyer);
    }

    @Override
    public Buyer getByName(String name) {
        return (Buyer) getSession().createCriteria(Buyer.class)
                .add(Restrictions.eq("name", name))// TODO: Kirill ну откуда ты этот дурацкий лайк взял?
                .uniqueResult();
    }

    @Override
    public void updateBalance(String buyerName, BigDecimal value) {
        getSession()
                .createQuery("update Buyer b set b.balance = b.balance + :value " +
                             "where b.name = :buyerName")// TODO: Kirill а что тут не лайк? а как я должен это понять из интерфейса который ты предоставляешь для своего дао?
                .setParameter("buyerName", buyerName)
                .setParameter("value", value)
                .executeUpdate();
    }

    @Override
    public BigDecimal getBalanceByName(String buyerName) {
        return (BigDecimal) getSession().createQuery("select b.balance as balance " +
                "from Buyer b where b.name = :buyerName")// TODO: Kirill или тут..
                .setParameter("buyerName", buyerName)
                .uniqueResult();
    }

    @Override
    public String getNameBySaleId(int saleId) {
        return (String) getSession()
                .createQuery("select b.name from Sale s " +
                             "left outer join s.buyer b on b.id = s.buyer.id " +
                             "where s.id = :saleId")
                .setParameter("saleId", saleId)
                .uniqueResult();
    }

}
