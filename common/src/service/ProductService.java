package service;

import dto.ProductDTO;
import util.PaginationBuilder;
import model.Product;
import java.util.List;

// TODO: Kirill ну раз начал по нормальному делать с дто, так делай полностью
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
