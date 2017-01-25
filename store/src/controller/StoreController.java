package controller;

import dto.*;
import entity.Comment;
import model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.*;
import util.CurrentUser;
import util.Pagination;
import util.PropertyApp;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
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

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @ModelAttribute("paginator")
    Pagination getPagination(){
        int numberAllRows = productService.getNumberAllRowsProduct();
        return new Pagination(numberAllRows);
    }

    @ModelAttribute("paginatorComment")
    Pagination getPaginationComment(){
        //int numberAllRows = commentService.countComment();
        return new Pagination();
    }

    @RequestMapping(value = {"/product", "/"})
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

    @RequestMapping(value = "/productInfo")
    public String productInfo(Model model, HttpServletRequest request,
                              @ModelAttribute("paginatorComment") Pagination pagination){
        int productId = Integer.parseInt(request.getParameter("id"));
        String folderProduct = productId + "/";
        List<String> fileNames = imageService.fileNameList(PropertyApp.PATH_PRODUCT_IMAGE + folderProduct);
        model.addAttribute("fileNames", fileNames);
        ProductDTO productDTO = productService.getProductDTOById(productId);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("commentList", commentService.list(productId, pagination));
        int numberAllRows = commentService.countComment(productId);
        pagination.setNumberAllRows(numberAllRows);
        int numberOfPages = pagination.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("url", "/store/productInfo?id=" + productId);

        model.addAttribute("commentDTO", new CommentDTO());

        return "productInfo";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("commentDTO") CommentDTO commentDTO){
        BuyerDTO buyer = buyerService.getDTOByName(CurrentUser.getCurrentUserName());
        commentDTO.setBuyerName(buyer.getName());
        commentDTO.setCreationDate(new Date());
        commentService.save(commentDTO);
        return "redirect:/productInfo?id=" + commentDTO.getProductId();
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model, HttpServletRequest request){
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        model.addAttribute("totalSum", basketService.getCost(basket));
        return "basket";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model){
        BuyerDTO buyer = buyerService.getDTOByName(CurrentUser.getCurrentUserName());
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
