package com.scheduler;

import com.service.DiscountService;
import com.service.ProductService;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MyTask {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private SaleService saleService;

    @Scheduled(cron = "0 0 0/1 * * *")
    public void insertDiscount(){
        discountService.insertProductDiscount();
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    public void aggregateSales(){
        saleService.aggregateSalesOfProductInTheLastHour();
    }

}
