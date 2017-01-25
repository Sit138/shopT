package com.controller;

import dto.DiscountDTO;
import util.enums.DiscountType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import util.Pagination;
import service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @ModelAttribute("paginator")
    Pagination getPagination(){
        int numberAllRows = discountService.countItemsDiscountHistory();
        return new Pagination(numberAllRows);
    }

    @RequestMapping(value = "/discountHistory")
    public String discountHistory(Model model,
                                  @ModelAttribute("paginator") Pagination pagination){
        int numberOfPages = pagination.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<DiscountDTO> productsDiscount = discountService.selectHistoryProductDiscounts(pagination);
        model.addAttribute("productsDiscount", productsDiscount);
        model.addAttribute("url", "/admin/discountHistory");
        return "discountHistory";
    }

    @RequestMapping(value = "/deleteDisc", method = RequestMethod.POST)
    public String deleteDiscount(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        discountService.insertEndDateDiscount(new Date(), id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/newDisc", method = RequestMethod.POST)
        public String newDiscount(HttpServletRequest request,
                                  @RequestParam("valDisc") byte value){
        int productId = Integer.parseInt(request.getParameter("id"));
        discountService.addProductDiscount(productId, value, DiscountType.Manual);
        return "redirect:/home";
    }

}
