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

public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
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
}
