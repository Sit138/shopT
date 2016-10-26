package com.dao.users;

import com.dto.users.UserDTO;
import com.model.security.User;

import java.util.List;

public interface UserDAO {

    void saveOrUpdate(User user);

    List<UserDTO> getUserDTOList();

}
