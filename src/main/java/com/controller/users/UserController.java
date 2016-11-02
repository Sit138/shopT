package com.controller.users;

import com.dto.users.RoleDTO;
import com.dto.users.UserDTO;
import com.model.security.Role;
import com.service.users.RoleService;
import com.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/users")
    public String usersPage(Model model){
        model.addAttribute("userDTOList", userService.getUserDTOList());
        return "admin/users";
    }

    @RequestMapping(value = "/admin/newUser")
    public String newUser(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        List<RoleDTO> rolesList = roleService.getListRoles();
        model.addAttribute("roleList", rolesList);
        return "admin/userForm";
    }

    @RequestMapping(value = "/admin/saveUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute UserDTO userDTO){
        userService.saveOrUpdate(userDTO);
        return "redirect:/admin/users";
    }

}
