package com.controller.users;

import com.dto.users.RoleDTO;
import com.model.security.Role;
import com.service.users.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/roles")
    public String rolesPage(){
        return "roles";
    }

    @RequestMapping(value = "/newRole")
    public String newRole(Model model){
        RoleDTO roleDTO = new RoleDTO();
        model.addAttribute("roleDTO", roleDTO);
        return "roleForm";
    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRole(@Valid @ModelAttribute RoleDTO roleDTO,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "roleForm";
        }
        Role role = new Role();
        role.setNameRole(roleDTO.getNameRole());
        roleService.saveOrUpdate(role);
        return "redirect:/roles";
    }

}
