package controller;

import dto.BuyerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.BuyerService;
import util.CurrentUser;
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
    public String deposit(@RequestParam("deposit") BigDecimal deposit){
        BuyerDTO buyerDTO = buyerService.getDTOByName(CurrentUser.getCurrentUserName());
        buyerService.addToBalance(buyerDTO.getId(), deposit);
        return "redirect:/profile";
    }

}
