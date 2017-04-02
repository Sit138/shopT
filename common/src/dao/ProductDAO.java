package dao;

import dto.ProductDTO;
import util.Pagination;
import entity.Product;
import java.util.List;

public interface ProductDAO extends GeneralDAO<Product> {

    List<ProductDTO> listProducts(Pagination pagination);

    int getNumberAllRowsProduct();

    Product getProduct(int id);

    void saveOrUpdate(Product product);

    void deleteProduct(int id);

    int getRandomIdWithoutDisc();

}
