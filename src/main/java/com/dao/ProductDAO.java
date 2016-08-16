package com.dao;

import com.model.Discount;
import com.model.Product;

import java.util.List;

public interface ProductDAO {

    public List<Product> listProducts();

    public Product getProduct(int id);

    public void saveOrUpdate(Product product);

    public void deleteProduct(int id);

    public Product getLastProduct();

    public void insertProductDiscount();

    public List<Discount> selectHistoryProductDiscounts();

    public Discount getNowDiscountProduct();

    public void insertProductSale(int id);


}
