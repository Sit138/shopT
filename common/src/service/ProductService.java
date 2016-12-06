package service;

import dto.ProductDTO;
import util.Pagination;
import java.util.List;

// TODO: Kirill ну раз начал по нормальному делать с дто, так делай полностью ++
public interface ProductService {

    List<ProductDTO> listProducts(Pagination pagination);

    int getNumberAllRowsProduct();

    ProductDTO getProductDTOById(int id);

    void saveOrUpdate(ProductDTO productDTO);

    void deleteProduct(int id);

    int getRandomIdWithoutDisc();

}
