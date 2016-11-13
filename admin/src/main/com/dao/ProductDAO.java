package com.dao;

import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.dto.util.PaginationBuilder;
import com.model.Product;

import java.util.Date;
import java.util.List;

public interface ProductDAO {

    List<ProductDTO> listProducts(PaginationBuilder paginationBuilder);

    int getNumberAllRowsProduct();

    ProductDTO getProductDTOById(int id);

    Product getProduct(int id);

    void saveOrUpdate(Product product);

    void deleteProduct(int id);

    ProductDTO getLastProduct();

    Product getRandomProduct();

}
