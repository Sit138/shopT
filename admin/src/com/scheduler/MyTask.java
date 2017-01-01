package com.scheduler;

import util.enums.DiscountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import service.DiscountService;
import service.ProductService;
import util.DiscountCalc;

import java.util.Date;

public class MyTask {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private ProductService productService;

    @Scheduled(cron = "0 0 0/1 * * *")
    public void insertAutoDiscount(){
        if(discountService.getNowAutoDiscountProduct() != null){
            discountService.insertEndDateDiscount(new Date(),
                                                  discountService.getNowAutoDiscountProduct().getProductId());
        }
        byte value = DiscountCalc.getRandomValueDiscount(5, 15);
        int productId = productService.getRandomIdWithoutDisc();
        discountService.addProductDiscount(productId, value, DiscountType.Auto);
    }

}
