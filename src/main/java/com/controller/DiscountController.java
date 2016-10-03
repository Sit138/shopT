package com.controller;

import com.dto.DiscountDTO;
import com.model.Discount;
import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DiscountController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/discountHistory", method = RequestMethod.GET)
    public String discountHistory(Model model, @RequestParam("num") int numberPage){
        int maxResult = 20;
        int countRows = productService.numberItemsDiscountHistory();
        int maxNumberPage = countRows / maxResult;
        model.addAttribute("maxNumberPage", maxNumberPage);
        int firstResult = numberPage * maxResult;
        List<DiscountDTO> productsDiscount = productService.selectHistoryProductDiscounts(firstResult, maxResult);
        model.addAttribute("productsDiscount", productsDiscount);
        model.addAttribute("numberPage", numberPage);
        model.addAttribute("pageUrl", "discountHistory");
        return "discountHistory";
    }

}
