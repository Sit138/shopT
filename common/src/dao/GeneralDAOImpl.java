package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneralDAOImpl<T> implements GeneralDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    /*private Class<T> entity;

    public GeneralDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entity = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }*/

    @Override
    public void save(T t) {
        getSession().saveOrUpdate(t);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
