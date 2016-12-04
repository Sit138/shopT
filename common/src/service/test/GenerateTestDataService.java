package service.test;

import dto.DiscountDTO;
import model.Discount;
import model.enums.DiscountType;
import model.Product;
import model.Sale;
import service.DiscountService;
import service.ProductService;
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
       /* generateAndSaveProduct();
        Calendar calendar = new GregorianCalendar(2016, 9, 5);//start day generate
        Calendar calendarNow = new GregorianCalendar();
        while (calendar.before(calendarNow)){
            generateAndSaveDiscount(calendar);
            System.out.println("Date = " + calendar.getTime());
            System.out.println("DateNow = " + calendarNow.getTime());
            int countSaleInHour = (int) (1 + (Math.random() * (50 - 1) + 1));//количество покупок в час
            for(int i = 0; i < countSaleInHour; i++){
                Product productSale = productService.getRandomProduct();
                BigDecimal saleAmount = productSale.getPrice();
                DiscountDTO discountProduct = discountService.getNowAutoDiscountProduct();
                if(discountProduct != null && (productSale.getId() == discountProduct.getProductId())){
                    double valueDiscount = discountProduct.getValue() / 100;
                    saleAmount = saleAmount.subtract(saleAmount.multiply(new BigDecimal(valueDiscount)));
                }
                //Sale sale = new Sale(saleAmount, productSale.getPrice(), calendar.getTime());
                //productSale.addProductSale(sale);
                productService.saveOrUpdate(productSale);
            }
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            discountService.insertEndDateDiscount(calendar.getTime(), discountService.getNowAutoDiscountProduct().getProductId());
        }*/
    }

    private void generateAndSaveProduct(){
        /*for(int i = 0; i < 50; i++){
            Product product = new Product();
            product.setName("Продукт " + i);
            int min = 50; int max = 1000;
            product.setPrice(BigDecimal.valueOf(min + (Math.random() * (max - min) + 1)));
            productService.saveOrUpdate(product);
        }*/
    }

    private void generateAndSaveDiscount(Calendar calendar){
        /*byte min = 5; byte max = 15;
        byte valueDiscount = (byte) (min + (Math.random() * (max - min) + 1));
        Discount discount = new Discount(valueDiscount, calendar.getTime(), DiscountType.Auto);
        Product productDiscount = productService.getRandomProduct();
        Hibernate.initialize(productDiscount.getDiscounts());
        productDiscount.addProductDiscont(discount);
        productService.saveOrUpdate(productDiscount);*/
    }

}
