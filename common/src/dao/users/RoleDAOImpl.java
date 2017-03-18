package dao.users;

import dto.users.RoleDTO;
import entity.security.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository(value = "roleDAO")
public class RoleDAOImpl implements RoleDAO {

    private SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Role role) {
        getSession().saveOrUpdate(role);
    }

    @Override
    public List<RoleDTO> getListRoles() {
        return getSession()
                .createQuery("select r.id as id, r.nameRole as nameRole from Role r")
                .setResultTransformer(Transformers.aliasToBean(RoleDTO.class))
                .list();
    }

    @Override
    public Role getRoleByName(String role) {
        return (Role) getSession()
                .createCriteria(Role.class)
                .add(Restrictions.eq("nameRole", role))
                .uniqueResult();
    }

    @Override
    public void deleteRole(int id) {
        getSession().
                createQuery("delete from Role where id = :id")
                .setParameter("id", id).executeUpdate();
    }
}
