package com.controller;

import com.dto.FinalStatisticSaleForPeriod;
import com.dto.StatisticOnSaleDTO;
import com.model.TestUtil;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @ModelAttribute("countRows")
    TestUtil getTestUtil(){
        TestUtil testUtil = new TestUtil();
        testUtil.setNumberRowsOnPage(10);
        testUtil.setNum(0);
        return testUtil;
    }

    @RequestMapping(value = "/statisticSale")
    public String saleProductPageByHour(Model model, @ModelAttribute("countRows") TestUtil testUtil){
        int maxResult = testUtil.getNumberRowsOnPage();
        int countRows = saleService.numberItemsTheSaleRangeReport();
        int maxNumberPage = countRows / maxResult;
        model.addAttribute("maxNumberPage", maxNumberPage);
        int numberPage = testUtil.getNum();
        System.out.println("NUM = " + numberPage);
        int firstResult = numberPage * maxResult;
        List<StatisticOnSaleDTO> statisticOnSaleList = saleService.getStatisticOnSale(firstResult, maxResult);
        model.addAttribute("statisticSale", statisticOnSaleList);
        model.addAttribute("numberPage", numberPage);
        FinalStatisticSaleForPeriod finalStatisticSaleForPeriod = saleService.getFinalStatisticSaleForPeriod();
        model.addAttribute("finalStatisticSaleForPeriod", finalStatisticSaleForPeriod);
        model.addAttribute("pageUrl", "statisticSale");
        return "statisticSale";
    }

}
