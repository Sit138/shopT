package controller;

import dto.Basket;
import dto.BuyerDTO;
import dto.ProductDTO;
import model.Buyer;
import model.Sale;
import org.hibernate.type.DoubleType;
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
        if(basket == null){
            basket = new Basket();
        }
        double discount = discountService.getNowDiscountProduct().getProductId() == productSaleId
                ? discountService.getNowDiscountProduct().getValue() : DoubleType.ZERO;
        basket.addProduct(product, count, discount);
        request.getSession().setAttribute("basket", basket);
        return "redirect:/product";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteProduct(HttpServletRequest request,
                                @RequestParam(value = "amount") int amount){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        int productId = Integer.parseInt(request.getParameter("id"));
        basket.deleteProduct(productId, amount);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/order")
    public String order(HttpServletRequest request){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        Buyer buyer = buyerService.getByName(CurrentUser.getCurrentUserName());
        Sale sale = new Sale(buyer, basket);
        saleService.save(sale);
        return "redirect:/profile";
    }

}
