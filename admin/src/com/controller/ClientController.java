package com.controller;

import com.dto.ProductDTO;
import com.dto.util.PaginationBuilder;
import com.service.DiscountService;
import com.service.ProductService;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private SaleService saleService;

    @ModelAttribute("paginator")
    PaginationBuilder getPaginationBuilder(){
        int numberAllRows = productService.getNumberAllRowsProduct();
        PaginationBuilder paginationBuilder = new PaginationBuilder(numberAllRows);
        return paginationBuilder;
    }

    @RequestMapping(value = "/client")
    public String shopClient(Model model,
                             @ModelAttribute("paginator") PaginationBuilder paginationBuilder){
        model.addAttribute("client", "Магазин");
        paginationBuilder.updateNumberFirstSamplingElement();
        int numberOfPages = paginationBuilder.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<ProductDTO> productList = productService.listProducts(paginationBuilder);
        model.addAttribute("productList", productList);
        if(discountService.getNowDiscountProduct() != null){
            model.addAttribute("discountNow", discountService.getNowDiscountProduct());
        } else {
            model.addAttribute("discountNow", null);
        }
        model.addAttribute("url", "/client");
        return "client";
    }

    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String saleProduct(HttpServletRequest request){
        int productSaleId = Integer.parseInt(request.getParameter("id"));
        saleService.insertProductSale(productSaleId);
        return "redirect:/client";
    }

}
