package com.controller.users;

import com.dto.users.RoleDTO;
import com.dto.users.UserDTO;
import com.service.users.RoleService;
import com.service.users.UserService;
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
    public String saveUser(@Valid @ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("roleList", roleService.getListRoles());
            return "admin/userForm";
        }
        userService.saveOrUpdate(userDTO);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }

}
