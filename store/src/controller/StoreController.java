package controller;

import dto.*;
import model.Buyer;
import org.springframework.security.core.context.SecurityContextHolder;
import service.BuyerService;
import service.SaleService;
import util.CurrentUser;
import util.PaginationBuilder;
import org.springframework.web.bind.annotation.*;
import service.DiscountService;
import service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes({"basket"})
public class StoreController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SaleService saleService;

    @ModelAttribute("paginator")
    PaginationBuilder getPaginationBuilder(){
        int numberAllRows = productService.getNumberAllRowsProduct();
        PaginationBuilder paginationBuilder = new PaginationBuilder(numberAllRows);
        return paginationBuilder;
    }

    @RequestMapping(value = {"/product", "/"})
    public String shopClient(Model model,
                             @ModelAttribute("paginator") PaginationBuilder paginationBuilder, HttpServletRequest request){
        model.addAttribute("client", "Магазин");
        paginationBuilder.updateNumberFirstSamplingElement();
        int numberOfPages = paginationBuilder.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<ProductDTO> productList = productService.listProducts(paginationBuilder);
        model.addAttribute("productList", productList);
        if(discountService.getNowDiscountProduct() != null){
            model.addAttribute("discountNow", discountService.getNowDiscountProduct());
        } else {
            model.addAttribute("discountNow", "Отсутствует");
        }
        model.addAttribute("username", CurrentUser.getCurrentUserName());
        return "product";
    }

    @RequestMapping(value = "/basket")
    public String basket(Model model, HttpServletRequest request){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        model.addAttribute("totalSum", basket.getCost());
        return "basket";
    }

    @RequestMapping(value = "/profile")
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
