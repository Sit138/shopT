package com.service;

import com.dao.ProductDAO;
import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> listProducts() {
        return productDAO.listProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductDTO(int id) {
        return productDAO.getProductDTO(id);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public ProductDTO getLastProduct() {
        return productDAO.getLastProduct();
    }

    @Override
    @Transactional
    public void insertProductDiscount() {
        productDAO.insertProductDiscount();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiscountDTO> selectHistoryProductDiscounts() {
        return productDAO.selectHistoryProductDiscounts();
    }

    @Override
    @Transactional(readOnly = true)
    public DiscountDTO getNowDiscountProduct() {
        return productDAO.getNowDiscountProduct();
    }

    @Override
    @Transactional
    public void insertProductSale(int id) {
        productDAO.insertProductSale(id);
    }
}
