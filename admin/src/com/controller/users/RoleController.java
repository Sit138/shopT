package com.controller.users;

import dto.users.RoleDTO;
import service.users.RoleService;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/setting/roles")
    public String rolesPage(Model model){
        List<RoleDTO> roleDTOList = roleService.getListRoles();
        model.addAttribute("roleDTOList", roleDTOList);
        return "setting/roles";
    }

    @RequestMapping(value = "/setting/newRole")
    public String newRole(Model model){
        RoleDTO roleDTO = new RoleDTO();
        model.addAttribute("roleDTO", roleDTO);
        return "setting/roleForm";
    }

    @RequestMapping(value = "/setting/saveRole", method = RequestMethod.POST)
    public String saveRole(@Valid @ModelAttribute RoleDTO roleDTO,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "setting/roleForm";
        }
        // TODO: Kirill этому не место в контроллере::убрал
        roleService.saveOrUpdate(roleDTO);
        return "redirect:/setting/roles";
    }

    @RequestMapping(value = "/setting/deleteRole", method = RequestMethod.GET)
    public String deleteRole(HttpServletRequest request){
        int roleId = Integer.parseInt(request.getParameter("id"));
        roleService.deleteRole(roleId);
        return "redirect:/setting/roles";
    }

}
