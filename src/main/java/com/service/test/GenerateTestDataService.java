package com.service.test;


import com.dto.DiscountDTO;
import com.model.Discount;
import com.model.Product;
import com.model.Sale;
import com.service.DiscountService;
import com.service.ProductService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Transactional
public class GenerateTestDataService {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscountService discountService;

    public void generateTestData(){
        generateAndSaveProduct();
        Calendar calendar = new GregorianCalendar(2016, 9, 1);//start day generate
        Calendar calendarNow = new GregorianCalendar();
        while (calendar.before(calendarNow)){
            generateAndSaveDiscount(calendar);
            System.out.println("Date = " + calendar.getTime());
            System.out.println("DateNow = " + calendarNow.getTime());
            int countSaleInHour = (int) (10 + (Math.random() * (10 - 1) + 1));//количество покупок в час
            for(int i = 0; i < countSaleInHour; i++){
                Product productSale = productService.getRandomProduct();
                BigDecimal saleAmount = productSale.getPrice();
                DiscountDTO discountProduct = discountService.getNowDiscountProduct();
                if(discountProduct != null && (productSale.getId() == discountProduct.getProductId())){
                    double valueDiscount = discountProduct.getValue() / 100;
                    saleAmount = saleAmount.subtract(saleAmount.multiply(new BigDecimal(valueDiscount)));
                }
                Sale sale = new Sale(saleAmount, productSale.getPrice(), calendar.getTime());
                Hibernate.initialize(productSale.getSales());
                productSale.addProductSale(sale);
                productService.saveOrUpdate(productSale);
            }
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            discountService.insertEndDateDiscount(1, calendar.getTime(), discountService.getNowDiscountProduct().getProductId());
        }
    }

    private void generateAndSaveProduct(){
        for(int i = 0; i < 50; i++){
            Product product = new Product();
            product.setName("Продукт " + i);
            int min = 50; int max = 1000;
            product.setPrice(BigDecimal.valueOf(min + (Math.random() * (max - min) + 1)));
            productService.saveOrUpdate(product);
        }
    }

    private void generateAndSaveDiscount(Calendar calendar){
        int min = 5; int max = 15;
        double valueDiscount = min + (Math.random() * (max - min) + 1);
        Discount discount = new Discount(valueDiscount, calendar.getTime(), 1);
        Product productDiscount = productService.getRandomProduct();
        Hibernate.initialize(productDiscount.getDiscounts());
        productDiscount.addProductDiscont(discount);
        productService.saveOrUpdate(productDiscount);
    }

}
