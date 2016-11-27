package dao;

import dto.BuyerDTO;
import model.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerDAOImpl implements BuyerDAO {

    private SessionFactory sessionFactory;

    public BuyerDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Buyer buyer) {
        getCurrentSession().save(buyer);
    }

    @Override
    public Buyer getByName(String name) {
        return (Buyer) getCurrentSession().createCriteria(Buyer.class)
                .add(Restrictions.like("name", name))
                .uniqueResult();
    }

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
