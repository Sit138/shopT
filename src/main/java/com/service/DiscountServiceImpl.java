package com.service;

import com.dao.DiscountDAO;
import com.dao.ProductDAO;
import com.dto.DiscountDTO;
import com.dto.util.PaginationBuilder;
import com.model.Discount;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDAO discountDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder) {
        return discountDAO.selectHistoryProductDiscounts(paginationBuilder);
    }

    @Override
    public DiscountDTO getNowDiscountProduct() {
        return discountDAO.getNowDiscountProduct();
    }

    @Override
    public int numberItemsDiscountHistory() {
        return discountDAO.numberItemsDiscountHistory();
    }

    @Override
    @Transactional
    public void insertEndDateDiscount(int addTypeDiscount, Date endDateDiscount, int productId) {
        discountDAO.insertEndDateDiscount(addTypeDiscount, endDateDiscount, productId);
    }

    @Override
    @Transactional
    public void insertProductDiscount() {
        Product productDiscount = productDAO.getRandomProduct();
        Discount discount = createDiscount(productDiscount);
        productDiscount.addProductDiscont(discount);
        productDAO.saveOrUpdate(productDiscount);
    }

    private Discount createDiscount(Product productDiscount){
        if(getNowDiscountProduct() != null){
            removeAutoDiscountNow();
        }
        Date currentDate = new Date();
        int min = 5; int max = 15;//нижнее/верхнее значение процентов скидки
        double newDiscount = min + (Math.random() * (max - min) + 1);
        Discount discount = new Discount(newDiscount, currentDate, 1);
        return discount;
    }

    private void removeAutoDiscountNow(){
        Date currentDate = new Date();
        int addType = 1;
        int productIdNowDiscount = getNowDiscountProduct().getProductId();
        insertEndDateDiscount(addType, currentDate, productIdNowDiscount);
    }
}
