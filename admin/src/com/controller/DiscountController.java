package com.controller;

import dto.DiscountDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import util.enums.DiscountType;
import util.Pagination;
import service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    @RequestMapping(value = "/discountHistory", method = RequestMethod.GET)
    public String discountHistory(Model model,
                                  @ModelAttribute("paginator") Pagination pagination){
        Calendar from = new GregorianCalendar();
        from.add(Calendar.DAY_OF_WEEK, -7);
        Date dateFrom = from.getTime();
        Date dateTo = new GregorianCalendar().getTime();
        model.addAttribute("startAt", dateFrom);
        model.addAttribute("endAt", dateTo);
        List<DiscountDTO> productsDiscount =
                discountService.getHistoryProductDiscounts(dateFrom, dateTo, pagination);
        model.addAttribute("productsDiscount", productsDiscount);
        model.addAttribute("url", "/admin/discountHistory");
        return "discountHistory";
    }

    @RequestMapping(value = "/discountHistory", method = RequestMethod.POST)
    public String discountHistory(Model model,
                                  @ModelAttribute("paginator") Pagination pagination,
                                  @RequestParam("startAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startAt,
                                  @RequestParam("endAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endAt){
        System.out.println("START DATE = " + startAt.toString());
        System.out.println("END DATE = " + endAt.toString());
        model.addAttribute("startAt", startAt);
        model.addAttribute("endAt", endAt);
        int numberOfPages = pagination.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<DiscountDTO> productsDiscount = discountService.getHistoryProductDiscounts(startAt, endAt, pagination);
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
