package com.controller;

import com.dto.FinalStatisticSaleForPeriod;
import com.dto.StatisticOnSaleDTO;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @RequestMapping(value = "/statisticSale", method = RequestMethod.GET)
    public String saleProductPageByHour(Model model, @RequestParam("num") int numberPage){
        int maxResult = 20;
        int countRows = saleService.numberItemsTheSaleRangeReport();
        int maxNumberPage = countRows / maxResult;
        model.addAttribute("maxNumberPage", maxNumberPage);
        int firstResult = numberPage * maxResult;
        List<StatisticOnSaleDTO> statisticOnSaleList = saleService.getStatisticOnSale(firstResult, maxResult);
        model.addAttribute("statisticSale", statisticOnSaleList);
        model.addAttribute("numberPage", numberPage);
        FinalStatisticSaleForPeriod finalStatisticSaleForPeriod = saleService.getFinalStatisticSaleForPeriod();
        model.addAttribute("finalStatisticSaleForPeriod", finalStatisticSaleForPeriod);
        return "statisticSale";
    }

}
