package com.controller;

import com.dto.DiscountDTO;
import com.dto.util.PaginationBuilder;
import com.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @ModelAttribute("paginator")
    PaginationBuilder getPaginationBuilder(){
        int numberAllRows = discountService.numberItemsDiscountHistory();
        PaginationBuilder paginationBuilder = new PaginationBuilder(numberAllRows);
        return paginationBuilder;
    }

    @RequestMapping(value = "/discountHistory")
    public String discountHistory(Model model,
                                  @ModelAttribute("paginator") PaginationBuilder paginationBuilder){
        paginationBuilder.updateNumberFirstSamplingElement();
        int numberOfPages = paginationBuilder.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<DiscountDTO> productsDiscount = discountService.selectHistoryProductDiscounts(paginationBuilder);
        model.addAttribute("productsDiscount", productsDiscount);
        model.addAttribute("url", "/discountHistory");
        return "discountHistory";
    }

}
