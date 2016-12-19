package controller;

import dto.BuyerDTO;
import model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.BuyerService;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model){
        if(error != null){
            model.addAttribute("error", "Неверное имя или пароль!");
        }
        if(logout != null){
            model.addAttribute("message", "Успешный выход!");
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("buyerDTO", new BuyerDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute BuyerDTO buyerDTO,
                               BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        try {
            Buyer buyer = new Buyer(buyerDTO);
            buyerService.save(buyer);
            return "redirect:/login";
        } catch (Exception e){
            model.addAttribute("errorSave", "Пользователь с таким логином уже существует!");
            return "registration";
        }
    }

}
