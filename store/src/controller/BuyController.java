package controller;

import model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.*;
import util.CurrentUser;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@SessionAttributes({"basket"})
public class BuyController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private BasketService basketService;

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public String putInBasket(HttpServletRequest request,
                              @RequestParam(value = "count") int count){
        int productSaleId = Integer.parseInt(request.getParameter("id"));
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        if(basket == null){
            basket = new Basket();
            request.getSession().setAttribute("basket", basket);
        }
        basketService.addProduct(basket, productSaleId, count);
        return "redirect:/product";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteProduct(HttpServletRequest request,
                                @RequestParam(value = "amount") int amount){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        int productId = Integer.parseInt(request.getParameter("id"));
        basketService.deleteProduct(basket, productId, amount);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)// TODO: Kirill метод какой?
    public String order(HttpServletRequest request){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        try {
            String buyerName = CurrentUser.getCurrentUserName();
            BigDecimal buyerBalance = buyerService.getBalanceByName(buyerName);
            BigDecimal basketCost = basketService.getCost(basket);
            if(basketCost.compareTo(buyerBalance) == -1){
                saleService.order(buyerName, basket);
                return "redirect:/profile";
            } else {
                return "redirect:/cashier";
            }
        } catch (Exception e){
            return "/basket";
        }
    }

}
