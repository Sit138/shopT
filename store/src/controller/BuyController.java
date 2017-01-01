package controller;

import model.Basket;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.BuyerService;
import service.DiscountService;
import service.ProductService;
import service.SaleService;
import util.CurrentUser;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@SessionAttributes({"basket"})
public class BuyController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public String putInBasket(HttpServletRequest request,
                              @RequestParam(value = "count") int count){
        int productSaleId = Integer.parseInt(request.getParameter("id"));
        ProductDTO product = productService.getProductDTOById(productSaleId);
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        // TODO: Kirill похоже это бизнес логика
        if(basket == null){
            basket = new Basket();
        }
        byte discount = product.isDiscounted() ? discountService.getValueByProductId(productSaleId) : 0;
        basket.addProduct(product, count, discount);
        //---------------------------------
        request.getSession().setAttribute("basket", basket);
        return "redirect:/product";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteProduct(HttpServletRequest request,
                                @RequestParam(value = "amount") int amount){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        int productId = Integer.parseInt(request.getParameter("id"));
        // TODO: Kirill и вообще в Корзине у тебя набор функций таких, это не просто pojo класс у тебя для передачи инфы
        // об отложенных продуктах туда и обратно. Надо подумать над улучшением.
        basket.deleteProduct(productId, amount);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)// TODO: Kirill метод какой?
    public String order(HttpServletRequest request){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        try {
            String buyerName = CurrentUser.getCurrentUserName();
            BigDecimal buyerBalance = buyerService.getBalanceByName(buyerName);
            BigDecimal basketCost = basket.getCost();
            if(basket.getCost().compareTo(buyerBalance) == -1){
                buyerService.updateBalance(buyerName, basketCost.negate());// TODO: Kirill вот тут все исполнилось
                saleService.save(buyerName, basket);// а тут искючение. Что получится в итоге? денег нет как и заказа.
                basket.clear();//все потому что бизнес логика не там где должна быть
                return "redirect:/profile";
            } else {
                return "redirect:/cashier";
            }
        } catch (Exception e){
            return "/basket";
        }
    }

}
