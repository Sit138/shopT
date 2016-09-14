package com.dao;

import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;

import java.util.List;

public interface ProductDAO {

    public List<ProductDTO> listProducts();

    public ProductDTO getProductDTOById(int id);

    public Product getProduct(int id);

    public void saveOrUpdate(Product product);

    public void deleteProduct(int id);

    public ProductDTO getLastProduct();

    public void insertProductDiscount();

    public List<DiscountDTO> selectHistoryProductDiscounts();

    public DiscountDTO getNowDiscountProduct();

    public void insertProductSale(int id);


}
