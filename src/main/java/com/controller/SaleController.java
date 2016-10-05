package com.controller;

import com.dto.FinalStatisticSaleForPeriod;
import com.dto.StatisticOnSaleDTO;
import com.dto.util.PaginationBuilder;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @ModelAttribute("paginator")
    PaginationBuilder getPaginationBuilder(){
        int numberAllRows = saleService.numberItemsTheSaleRangeReport();
        PaginationBuilder paginationBuilder = new PaginationBuilder(numberAllRows);
        return paginationBuilder;
    }

    @RequestMapping(value = "/statisticSale")
    public String saleProductPageByHour(Model model,
                                        @ModelAttribute("paginator") PaginationBuilder paginationBuilder){
        paginationBuilder.updateNumberFirstSamplingElement();
        int numberOfPages = paginationBuilder.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<StatisticOnSaleDTO> statisticOnSaleList = saleService.getStatisticOnSale(paginationBuilder);
        model.addAttribute("statisticSale", statisticOnSaleList);
        FinalStatisticSaleForPeriod finalStatisticSaleForPeriod = saleService.getFinalStatisticSaleForPeriod();
        model.addAttribute("finalStatisticSaleForPeriod", finalStatisticSaleForPeriod);
        model.addAttribute("url", "/statisticSale");
        return "statisticSale";
    }

}
