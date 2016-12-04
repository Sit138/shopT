package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.BuyerService;
import util.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
public class CashController {

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/cashier")
    public String cashier(){
        return "cashier";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String deposit(HttpServletRequest request,
                          @RequestParam("deposit") BigDecimal deposit){
        buyerService.updateBalance(CurrentUser.getCurrentUserName(), deposit);
        return "redirect:/profile";
    }

}
