package com.controller;


import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;
import com.service.ProductService;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/saleTime")
    public String saleProductPageByHour(Model model){
        List<SaleProductInRangeDetailed> saleInRangeDetailedList = saleService.saleListInRange();
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
