package com.controller;

import dto.FinalStatisticSaleForPeriod;
import dto.StatisticOnSaleDTO;
import dto.util.FilterStatisticSale;
import dto.util.PaginationBuilder;
import service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ModelAttribute("paginator")
    PaginationBuilder getPaginationBuilder(){
        Calendar from = new GregorianCalendar();
        Calendar to = new GregorianCalendar();
        from.add(Calendar.DAY_OF_WEEK, -7);
        int numberAllRows = saleService.numberItemsTheSaleRangeReport(from.getTime(), to.getTime());
        FilterStatisticSale filterStatisticSale = new FilterStatisticSale(numberAllRows);
        return filterStatisticSale;
    }

    @RequestMapping(value = "/statisticSale")
    public String saleProductPageByHour(Model model,
                                        @ModelAttribute("paginator") FilterStatisticSale paginationBuilder) {
        paginationBuilder.updateNumberFirstSamplingElement();
        paginationBuilder.setNumberAllRows(saleService.numberItemsTheSaleRangeReport(
                paginationBuilder.getFromDate(), paginationBuilder.getToDate()));
        int numberOfPages = paginationBuilder.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<StatisticOnSaleDTO> statisticOnSaleList = saleService.getStatisticOnSale(paginationBuilder);
        model.addAttribute("statisticSale", statisticOnSaleList);
        FinalStatisticSaleForPeriod finalStatisticSaleForPeriod =
                saleService.getFinalStatisticSaleForPeriod(paginationBuilder.getFromDate(), paginationBuilder.getToDate());
        System.out.println("Диапазон = " + paginationBuilder.getFromDate() + " - " + paginationBuilder.getToDate());
        model.addAttribute("finalStatisticSaleForPeriod", finalStatisticSaleForPeriod);
        model.addAttribute("url", "/statisticSale");
        return "statisticSale";
    }

}
