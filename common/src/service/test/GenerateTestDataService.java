package service.test;

import dto.ProductDTO;
import model.enums.DiscountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DiscountService;
import service.ProductService;
import util.DiscountCalc;
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

    public void generateAndSaveProduct(){
        for(int i = 0; i < 50; i++){
            ProductDTO product = new ProductDTO();
            product.setName("Продукт " + i);
            int min = 50; int max = 1000;
            product.setPrice(BigDecimal.valueOf(min + (Math.random() * (max - min) + 1)));
            productService.saveOrUpdate(product);
        }
    }

    public void generateAndSaveDiscount(){
        Calendar calendar = new GregorianCalendar(2016, 10, 5);
        Calendar calendarNow = new GregorianCalendar();

        while (calendar.before(calendarNow)){
            if(discountService.getNowAutoDiscountProduct() != null){
                discountService.insertEndDateDiscount(calendar.getTime(),
                        discountService.getNowAutoDiscountProduct().getProductId());
            }
            byte value = DiscountCalc.getRandomValueDiscount(5, 15);
            int productId = productService.getRandomIdWithoutDisc();
            discountService.addProductDiscount(productId, value, DiscountType.Auto);

            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }
    }

}
