package com.controller;


import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;
import com.service.ProductService;
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
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/saleTime", method = RequestMethod.GET)
    public String saleProductPageByHour(Model model, @RequestParam("pageId") int pageId){
        int maxResult = 10;
        int num = saleService.numberItemsTheSaleRangeReport();
        int maxPageId = num / maxResult;
        model.addAttribute("maxPageId", maxPageId);
        pageId = pageId * maxResult;
        List<SaleProductInRangeDetailed> saleInRangeDetailedList = saleService.saleListInRangePagination(pageId, maxResult);
        model.addAttribute("sale", saleInRangeDetailedList);

        return "saleTime";
    }

    @RequestMapping(value = "/totalSaleReport")
    public String totalSaleReport(Model model){
        List<TotalSaleReport> totalSaleReportList = saleService.totalSaleReport();
        model.addAttribute("totalSaleReport", totalSaleReportList);
        return "totalSaleReport";
    }

}
