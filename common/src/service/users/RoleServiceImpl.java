package service.users;

import dao.users.RoleDAO;
import dto.users.RoleDTO;
import entity.security.Role;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public void saveOrUpdate(RoleDTO roleDTO) {
        Role role = new Role();
        role.setNameRole(roleDTO.getNameRole());
        roleDAO.saveOrUpdate(role);
    }

    @Override
    public List<RoleDTO> getListRoles() {
        return roleDAO.getListRoles();
    }

    @Override
    @Transactional
    public void deleteRole(int id) {
        try {
            roleDAO.deleteRole(id);
            // TODO: Kirill а происходит он почему?
            // когда пытаемся удалить роль, которую используют пользователи
        } catch (ConstraintViolationException e){
            return;
        }
    }
}
