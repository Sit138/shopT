package com.dao.users;

import com.dto.users.UserDTO;
import com.model.security.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.util.List;

// TODO: Kirill всегда можно попробовать ради тренировки одни и тежи запросы сделать в разных видах реализации
// мало того что потренируешься, так еще и будет понятно со временем что где гармоничнее смотрится и больше подходит
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(User user) {
        getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<UserDTO> getUserDTOList() {
        return getCurrentSession()
                .createSQLQuery("SELECT u.id AS id, " +
                                       "u.username AS userName, " +
                                       "u.password AS password, " +
                                       "r.name_role AS nameRole, " +
                                       "u.enabled AS enabled " +
                                "FROM user_account u " +
                                "LEFT OUTER JOIN role r ON r.id = u.role_id " +
                                "ORDER BY u.id ASC")
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("password", StringType.INSTANCE)
                .addScalar("nameRole", StringType.INSTANCE)
                .addScalar("enabled", BooleanType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(UserDTO.class))
                .list();
    }

    @Override
    public void deleteUser(int id) {
        getCurrentSession()
                .createSQLQuery("DELETE FROM user_account WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public UserDTO getUserDTOById(int id) {
        return (UserDTO) getCurrentSession()
                .createSQLQuery("SELECT u.id AS id, " +
                                    "u.username AS userName, " +
                                    "u.password AS password, " +
                                    "r.name_role AS nameRole, " +
                                    "u.enabled AS enabled " +
                                "FROM user_account u " +
                                "LEFT OUTER JOIN role r ON r.id = u.role_id " +
                                "WHERE u.id = :id")
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("password", StringType.INSTANCE)
                .addScalar("nameRole", StringType.INSTANCE)
                .addScalar("enabled", BooleanType.INSTANCE)
                .setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(UserDTO.class))
                .uniqueResult();
    }

    @Override
    public User getUserById(int id) {// TODO: Kirill а что не так?
        return (User) getCurrentSession()//.get(User.class, id)
                .createQuery("from User u where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }
}
