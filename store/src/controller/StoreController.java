package controller;

import dto.*;
import model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BasketService;
import service.BuyerService;
import service.ProductService;
import service.SaleService;
import util.CurrentUser;
import util.Pagination;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes({"basket"})
public class StoreController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private BasketService basketService;

    @ModelAttribute("paginator")
    Pagination getPagination(){
        int numberAllRows = productService.getNumberAllRowsProduct();
        Pagination pagination = new Pagination(numberAllRows);
        return pagination;
    }

    @RequestMapping(value = {"/product", "/"}, method = RequestMethod.GET)
    public String shopClient(Model model,
                             @ModelAttribute("paginator") Pagination pagination){
        model.addAttribute("client", "Магазин");
        int numberOfPages = pagination.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<ProductDTO> productList = productService.listProducts(pagination);
        model.addAttribute("productList", productList);
        model.addAttribute("username", CurrentUser.getCurrentUserName());
        return "product";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model, HttpServletRequest request){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        model.addAttribute("totalSum", basketService.getCost(basket));
        return "basket";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model){
        BuyerDTO buyer = buyerService.getByNameDTO(CurrentUser.getCurrentUserName());
        model.addAttribute("buyer", buyer);
        List<SaleDTO> sales = saleService.getByBuyerId(buyer.getId());
        model.addAttribute("sales", sales);
        return "profile";
    }

    @RequestMapping(value = "/profile/orderInfo", method = RequestMethod.GET)
    public String orderInformation(Model model,
                                   @RequestParam("saleId") int saleId){
        List<SoldProductDTO> orderProducts = saleService.getOrderInfo(saleId);
        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("saleId", saleId);
        return "orderInfo";
    }

}
