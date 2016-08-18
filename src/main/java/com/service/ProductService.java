package com.service;

import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> listProducts();

    public ProductDTO getProduct(int id);

    public void saveOrUpdate(Product product);

    public void deleteProduct(int id);

    public ProductDTO getLastProduct();

    public void insertProductDiscount();

    public List<Discount> selectHistoryProductDiscounts();

    public Discount getNowDiscountProduct();

    public void insertProductSale(int id);

}
