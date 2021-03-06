package service.users;

import dto.users.RoleDTO;

import java.util.List;

public interface RoleService {

    void saveOrUpdate(RoleDTO roleDTO);

    List<RoleDTO> getListRoles();

    void deleteRole(int id);

}
