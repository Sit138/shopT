package service.users;

import dto.users.UserDTO;
import model.security.User;
import java.util.List;

public interface UserService {

    void saveOrUpdate(User user);

    void saveOrUpdate(UserDTO userDTO);

    List<UserDTO> getUserDTOList();

    void deleteUser(int id);

    UserDTO getUserDTOById(int id);
}
