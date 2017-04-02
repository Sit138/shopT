package dao.users;

import dao.GeneralDAO;
import dto.users.RoleDTO;
import entity.security.Role;
import java.util.List;

public interface RoleDAO extends GeneralDAO<Role> {

    void saveOrUpdate(Role role);

    List<RoleDTO> getListRoles();

    Role getRoleByName(String role);

    void deleteRole(int id);

}
