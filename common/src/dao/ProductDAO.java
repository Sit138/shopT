package dao;

import dto.ProductDTO;
import util.Pagination;
import model.Product;
import java.util.List;

public interface ProductDAO {

    List<ProductDTO> listProducts(Pagination pagination);

    int getNumberAllRowsProduct();

    ProductDTO getProductDTOById(int id);

    Product getProduct(int id);

    void saveOrUpdate(Product product);

    void deleteProduct(int id);

    int getRandomIdWithoutDisc();

}
