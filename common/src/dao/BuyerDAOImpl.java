package dao;

import dto.BuyerDTO;
import entity.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

// TODO: Kirill почему ты используешь везде имя вместо айди?::Сейчас имя уникально, пока оставил (сделаю через ID)
@Repository(value = "buyerDAO")
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
    public Buyer getById(int id) {
        return getSession().get(Buyer.class, id);
    }

    @Override
    public List<BuyerDTO> list() {
        return getSession()
                .createQuery("select b.id as id, " +
                                    "b.balance as balance, " +
                                    "b.name as name, " +
                                    "b.enabled as enabled, " +
                                    "b.password as password, " +
                                    "b.registrationDate as registrationDate " +
                            "from Buyer b")
                .setResultTransformer(Transformers.aliasToBean(BuyerDTO.class))
                .list();
    }

    @Override
    public void addToBalance(int id, BigDecimal value) {
        getSession()
                .createQuery("update Buyer b set b.balance = b.balance + :value " +
                             "where b.id = :id")// TODO: Kirill а что тут не лайк? а как я должен это понять из интерфейса который ты предоставляешь для своего дао?
                .setParameter("id", id)
                .setParameter("value", value)
                .executeUpdate();
    }

    @Override
    public BigDecimal getBalanceById(int id) {
        return (BigDecimal) getSession().createQuery("select b.balance as balance " +
                "from Buyer b where b.id = :id")// TODO: Kirill или тут..
                .setParameter("id", id)
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
