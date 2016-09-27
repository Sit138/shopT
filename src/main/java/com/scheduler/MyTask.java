package com.scheduler;

import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MyTask {

    @Autowired
    private ProductService productService;

    @Scheduled(cron = "0 0/5 * * * *")
    public void work(){
        productService.insertProductDiscount();
    }

}
