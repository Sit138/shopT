package com.service.users;

import com.dto.users.RoleDTO;
import com.model.security.Role;

import java.util.List;

public interface RoleService {

    void saveOrUpdate(Role role);

    List<RoleDTO> getListRoles();

    void deleteRole(int id);

}
