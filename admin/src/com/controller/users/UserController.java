package com.controller.users;

import dto.users.RoleDTO;
import dto.users.UserDTO;
import service.users.RoleService;
import service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/setting/users", method = RequestMethod.GET)
    public String usersPage(Model model){
        model.addAttribute("userDTOList", userService.getUserDTOList());
        return "setting/users";
    }

    @RequestMapping(value = "/setting/newUser", method = RequestMethod.GET)
    public String newUser(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        List<RoleDTO> rolesList = roleService.getListRoles();
        model.addAttribute("roleList", rolesList);
        return "setting/userForm";
    }

    @RequestMapping(value = "/setting/saveUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("roleList", roleService.getListRoles());
            return "setting/userForm";
        }
        userService.saveOrUpdate(userDTO);
        return "redirect:/setting/users";
    }

    @RequestMapping(value = "/setting/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(userId);
        return "redirect:/setting/users";
    }

}
