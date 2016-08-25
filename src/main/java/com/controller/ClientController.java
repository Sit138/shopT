package com.controller;


import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class ClientController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/client")
    public String shopClient(Model model){
        model.addAttribute("client", "Магазин");
        List<ProductDTO> productList = productService.listProducts();
        model.addAttribute("productList", productList);
        if(productService.getNowDiscountProduct() != null){
            model.addAttribute("discountNow", productService.getNowDiscountProduct());
        } else {
            model.addAttribute("discountNow", null);
        }

        return "client";
    }

    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String saleProduct(HttpServletRequest request){
        int productSaleId = Integer.parseInt(request.getParameter("id"));
        productService.insertProductSale(productSaleId);
        return "redirect:/client";
    }

}
