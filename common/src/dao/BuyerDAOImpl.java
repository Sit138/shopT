package dao;

import dto.BuyerDTO;
import model.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

// TODO: Kirill почему ты используешь везде имя вместо айди?
@Repository
public class BuyerDAOImpl implements BuyerDAO {

    private SessionFactory sessionFactory;

    public BuyerDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }// TODO: Kirill getSession()

    @Override
    public void save(Buyer buyer) {
        getCurrentSession().save(buyer);
    }

    @Override
    public Buyer getByName(String name) {
        return (Buyer) getCurrentSession().createCriteria(Buyer.class)
                .add(Restrictions.like("name", name))// TODO: Kirill ну откуда ты этот дурацкий лайк взял?
                .uniqueResult();
    }

    @Override
    public void updateBalance(String buyerName, BigDecimal value) {
        getCurrentSession()
                .createQuery("update Buyer b set b.balance = b.balance + :value " +
                             "where b.name = :buyerName")// TODO: Kirill а что тут не лайк? а как я должен это понять из интерфейса который ты предоставляешь для своего дао?
                .setParameter("buyerName", buyerName)
                .setParameter("value", value)
                .executeUpdate();
    }

    @Override
    public BigDecimal getBalanceByName(String buyerName) {
        return (BigDecimal) getCurrentSession().createQuery("select b.balance as balance " +
                "from Buyer b where b.name = :buyerName")// TODO: Kirill или тут..
                .setParameter("buyerName", buyerName)
                .uniqueResult();
    }

    @Override
    public String getNameBySaleId(int saleId) {
        return (String) getCurrentSession()
                .createQuery("select b.name from Sale s " +
                             "left outer join s.buyer b on b.id = s.buyer.id " +
                             "where s.id = :saleId")
                .setParameter("saleId", saleId)
                .uniqueResult();
    }

    @Override
    public BuyerDTO getByNameDTO(String name){
        return (BuyerDTO) getCurrentSession().createQuery("select b.id as id, b.name as name, " +
                "b.password as password, b.enabled as enabled, b.balance as balance, " +
                "b.registrationDate as registrationDate " +
                "from Buyer b where b.name = :username")
                .setParameter("username", name)
                .setResultTransformer(Transformers.aliasToBean(BuyerDTO.class))
                .uniqueResult();
    }
}
