package com.scheduler;

import model.Product;
import model.enums.DiscountType;
import service.DiscountService;
import service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
        //Product product = productService.getRandomProduct();
        int productId = productService.getRandomIdWithoutDisc();
        discountService.addProductDiscount(productId, value, DiscountType.Auto);
    }

    /*@Scheduled(cron = "0 0 0/1 * * *")
    public void aggregateSales(){
        saleService.aggregateSalesOfProductInTheLastHour();
    }*/

}
