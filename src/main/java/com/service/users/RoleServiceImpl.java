package com.service.users;


import com.dao.users.RoleDAO;
import com.dto.users.RoleDTO;
import com.model.security.Role;
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
    public void saveOrUpdate(Role role) {
        roleDAO.saveOrUpdate(role);
    }

    @Override
    public List<RoleDTO> getListRoles() {
        return roleDAO.getListRoles();
    }
}
