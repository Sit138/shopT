package dao.users;

import dto.users.UserDTO;
import entity.security.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(User user) {
        getSession().saveOrUpdate(user);
    }

    @Override
    public List<UserDTO> getUserDTOList() {
        return getSession()
                .createQuery("select u.id as id, u.userName as userName, u.password as password, " +
                        "r.nameRole as nameRole, u.enabled as enabled " +
                        "from User u left outer join u.role r on r.id = u.role.id " +
                        "order by u.id asc")
                .setResultTransformer(Transformers.aliasToBean(UserDTO.class))
                .list();
    }

    @Override
    public void deleteUser(int id) {
        getSession()
                .createQuery("delete from User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User getUserById(int id) {
        return getSession()
                .get(User.class, id);
    }
}
