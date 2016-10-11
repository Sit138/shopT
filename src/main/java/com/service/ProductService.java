package com.service;

import com.dto.ProductDTO;
import com.dto.util.PaginationBuilder;
import com.model.Product;
import java.util.List;

public interface ProductService {

    List<ProductDTO> listProducts(PaginationBuilder paginationBuilder);

    int getNumberAllRowsProduct();

    ProductDTO getProductDTOById(int id);

    Product getProduct(int id);

    void saveOrUpdate(Product product);

    void deleteProduct(int id);

    ProductDTO getLastProduct();

    Product getRandomProduct();

}
