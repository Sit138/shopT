package com.service;

import com.dao.ProductDAO;
import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;
import com.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<ProductDTO> listProducts() {
        return productDAO.listProducts();
    }

    @Override
    public ProductDTO getProductDTOById(int id) {
        return productDAO.getProductDTOById(id);
    }

    @Override
    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Product product) {
        productDAO.saveOrUpdate(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    @Override
    public ProductDTO getLastProduct() {
        return productDAO.getLastProduct();
    }

    @Override
    @Transactional
    public void insertProductDiscount() {
        Product productDiscount = productDAO.getRandomProduct();
        Discount discount = generateDiscount(productDiscount);
        productDiscount.addProductDiscont(discount);
        saveOrUpdate(productDiscount);
        //productDAO.insertProductDiscount();
    }

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts() {
        return productDAO.selectHistoryProductDiscounts();
    }

    @Override
    public DiscountDTO getNowDiscountProduct() {
        return productDAO.getNowDiscountProduct();
    }

    @Override
    @Transactional
    public void insertProductSale(int id) {
        Product productSale = getProduct(id);
        Sale sale = generateSale(productSale);
        productSale.addProductSale(sale);
        saveOrUpdate(productSale);
    }

    private Sale generateSale(Product productSale){
        Date currentDate = new Date();
        BigDecimal saleAmount = productSale.getPrice();
        DiscountDTO discountProduct = getNowDiscountProduct();
        if(discountProduct != null && (productSale.getId() == discountProduct.getProductId())){
            double valueDiscount = discountProduct.getValue() / 100;
            saleAmount = saleAmount.subtract(saleAmount.multiply(new BigDecimal(valueDiscount)));
        }
        Sale sale = new Sale(saleAmount, currentDate);
        return sale;
    }

    private Discount generateDiscount(Product productDiscount){
        Date currentDate = new Date();
        int min = 5; int max = 15;//нижнее/верхнее значение процентов скидки
        double newDiscount = min + (Math.random() * (max - min) + 1);

        BigDecimal productDiscountPrice = productDiscount.getPrice()
                .subtract(productDiscount.getPrice()
                        .multiply(new BigDecimal(newDiscount / 100)));
        BigDecimal discountPriceSpread = productDiscount.getPrice().subtract(productDiscountPrice);
        Discount discount = new Discount(newDiscount, currentDate, productDiscountPrice, discountPriceSpread);
        return discount;
    }

}
