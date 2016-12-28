package service.users;

import dao.users.RoleDAO;
import dao.users.UserDAO;
import dto.users.UserDTO;
import model.security.Role;
import model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
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
        User user = userDAO.getUserById(userDTO.getId());
        if(user == null){
            user = new User();
        }
        Role role = roleDAO.getRoleByName(userDTO.getNameRole());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.isEnabled());
        user.setRole(role);
        //roleDAO.saveOrUpdate(role);// TODO: Kirill все равно это стремак какой-то - сохранять пользователя через соъранение роли с каскадом.
        //::убрал
        userDAO.saveOrUpdate(user);
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


}
