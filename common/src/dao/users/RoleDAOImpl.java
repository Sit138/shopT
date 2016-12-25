package dao.users;

import dto.users.RoleDTO;
import model.security.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Role role) {
        getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public List<RoleDTO> getListRoles() {
        return getCurrentSession()
                // TODO: Kirill не надо писать скл когда можно легко использовать hql или criteria
                // поправил, где очень толсто, продолжаю изучать
                .createQuery("select r.id as id, r.nameRole as nameRole from Role r")
                .setResultTransformer(Transformers.aliasToBean(RoleDTO.class))
                .list();
    }

    @Override
    public Role getRoleByName(String role) {
        return (Role) getCurrentSession()
                .createCriteria(Role.class)
                .add(Restrictions.eq("nameRole", role))// TODO: Kirill какой нафик лайк? По-твоему вместо manager подойдет,
                // например, content_manager или какой там еще у тебя будет
                //нет. Заменил на equal
                .uniqueResult();
    }

    @Override
    public void deleteRole(int id) {
        getCurrentSession().
                createQuery("delete from Role where id = :id")
                .setParameter("id", id).executeUpdate();
    }
}
