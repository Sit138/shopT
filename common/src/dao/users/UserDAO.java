package dao.users;

import dao.GeneralDAO;
import dto.users.UserDTO;
import entity.security.User;
import java.util.List;

public interface UserDAO extends GeneralDAO<User> {

    void saveOrUpdate(User user);

    List<UserDTO> getUserDTOList();

    void deleteUser(int id);

    User getUserById(int id);

}
