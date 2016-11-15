package service;

import dao.DiscountDAO;
import dao.ProductDAO;
import dto.ProductDTO;
import dto.util.PaginationBuilder;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private DiscountDAO discountDAO;

    @Override
    public List<ProductDTO> listProducts(PaginationBuilder paginationBuilder) {
        return productDAO.listProducts(paginationBuilder);
    }

    @Override
    public int getNumberAllRowsProduct() {
        return productDAO.getNumberAllRowsProduct();
    }

    @Override
    public ProductDTO getProductDTOById(int id) {
        return productDAO.getProductDTOById(id);
    }

    @Override
    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Product product) {
        productDAO.saveOrUpdate(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    @Override
    public ProductDTO getLastProduct() {
        return productDAO.getLastProduct();
    }

    @Override
    public Product getRandomProduct() {
        return productDAO.getRandomProduct();
    }

}
