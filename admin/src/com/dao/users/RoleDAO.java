package com.dao.users;

import com.dto.users.RoleDTO;
import com.model.security.Role;
import java.util.List;

public interface RoleDAO {

    void saveOrUpdate(Role role);

    List<RoleDTO> getListRoles();

    Role getRoleByName(String role);

    void deleteRole(int id);

}
