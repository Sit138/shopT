package com.scheduler;

import com.service.ProductService;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MyTask {

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Scheduled(cron = "0 0/5 * * * *")
    public void insertDiscount(){
        productService.insertProductDiscount();
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    public void aggregateSales(){
        saleService.aggregateSalesOfProductInTheLastHour();
    }

}
