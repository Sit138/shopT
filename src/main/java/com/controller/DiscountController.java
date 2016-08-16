package com.controller;

import com.model.Discount;
import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DiscountController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/discountHistory")
    public String discountHistory(Model model){
        List<Discount> productsDiscount = productService.selectHistoryProductDiscounts();
        model.addAttribute("productsDiscount", productsDiscount);
        return "discountHistory";
    }

}
