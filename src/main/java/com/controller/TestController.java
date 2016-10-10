package com.controller;

import com.model.Product;
import com.service.DiscountService;
import com.service.ProductService;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class TestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private SaleService saleService;

    @RequestMapping(value = "/test")
    public String generateTestData(){
        for(int i = 0; i < 100; i++){
            Product product = new Product();
            product.setName("Продукт " + i);
            int min = 50; int max = 1000;
            product.setPrice(BigDecimal.valueOf(min + (Math.random() * (max - min) + 1)));
            productService.saveOrUpdate(product);
        }
        for(int j = 0; j < 1000; j++){
            saleService.insertProductSale((int) (1 + (Math.random() * (100 - 1) + 1)));
        }
        return "test";
    }

}
