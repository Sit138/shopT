package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.BuyerService;

@Controller
public class UserController {

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model){
        model.addAttribute("buyerList", buyerService.list());
        return "users";
    }
}
