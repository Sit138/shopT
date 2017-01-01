package dao.users;

import dto.users.RoleDTO;
import entity.security.Role;
import java.util.List;

public interface RoleDAO {

    void saveOrUpdate(Role role);

    List<RoleDTO> getListRoles();

    Role getRoleByName(String role);

    void deleteRole(int id);

}
