package com.service;

import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;

import java.util.Date;
import java.util.List;

public interface ProductService {

    List<ProductDTO> listProducts();

    ProductDTO getProductDTOById(int id);

    Product getProduct(int id);

    void saveOrUpdate(Product product);

    void deleteProduct(int id);

    ProductDTO getLastProduct();

    void insertProductDiscount();

    List<DiscountDTO> selectHistoryProductDiscounts(int firstResult, int maxCounRows);

    DiscountDTO getNowDiscountProduct();

    void insertProductSale(int id);

    int numberItemsDiscountHistory();

    void insertEndDateDiscount(int addTypeDiscount, Date endDateDiscount, int productId);

}
