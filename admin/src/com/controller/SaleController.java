package com.controller;

import dto.SaleDTO;
import dto.SoldProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.SaleService;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @RequestMapping(value = "/sale")
    public String sale(Model model){
        List<SaleDTO> sales = saleService.list();
        model.addAttribute("sales", sales);
        return "sale";
    }

    @RequestMapping(value = "/sale/orderInfo", method = RequestMethod.GET)
    public String orderInformation(Model model,
                                   @RequestParam("saleId") int saleId){
        List<SoldProductDTO> orderProducts = saleService.getOrderInfo(saleId);
        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("saleId", saleId);
        return "orderInfo";
    }

}
