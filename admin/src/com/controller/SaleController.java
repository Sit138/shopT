package com.controller;

import dto.SaleDTO;
import dto.SoldProductDTO;
import util.enums.SaleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.BuyerService;
import service.SaleService;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/sale")
    public String sale(Model model){
        List<SaleDTO> sales = saleService.list();
        model.addAttribute("sales", sales);
        model.addAttribute("stateSale", SaleState.saleStates);
        return "sale";
    }

    @RequestMapping(value = "/sale/orderInfo", method = RequestMethod.GET)
    public String orderInformation(Model model, @RequestParam("saleId") int saleId){
        List<SoldProductDTO> orderProducts = saleService.getOrderInfo(saleId);
        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("saleId", saleId);
        model.addAttribute("buyer", buyerService.getNameBySaleId(saleId));
        return "orderInfo";
    }

    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    public String updateState(HttpServletRequest request,
                              @RequestParam("state") String state,
                              @RequestParam("totalSum") BigDecimal totalSum){
        int saleId = Integer.parseInt(request.getParameter("saleId"));// TODO: Kirill null or "asdf"  
        SaleState newState = SaleState.parse(state);
        if(newState.equals(SaleState.CANCELED)){
            String buyerName = buyerService.getNameBySaleId(saleId);
            buyerService.updateBalance(buyerName, totalSum);
        }
        saleService.updateState(saleId, newState);
        return "redirect:/sale";
    }

}
