package com.service.users;

import com.dao.users.RoleDAO;
import com.dao.users.UserDAO;
import com.dto.users.UserDTO;
import com.model.security.Role;
import com.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public void saveOrUpdate(User user) {
        userDAO.saveOrUpdate(user);
    }

    @Transactional
    public void saveOrUpdate(UserDTO userDTO){
        User user;
        // TODO: Kirill зачем это все? сразу почему бы не поискать юзера нужного?
        if(getUserDTOById(userDTO.getId()) == null){
            user = new User();
        } else {
            user = userDAO.getUserById(userDTO.getId());
        }

        Role role = roleDAO.getRoleByName(userDTO.getNameRole());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.isEnabled());
        role.addUser(user);
        roleDAO.saveOrUpdate(role);
    }

    @Override
    public List<UserDTO> getUserDTOList() {
        return userDAO.getUserDTOList();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    // TODO: Kirill похоже не нужен
    @Override
    public UserDTO getUserDTOById(int id) {
        return userDAO.getUserDTOById(id);
    }

}
