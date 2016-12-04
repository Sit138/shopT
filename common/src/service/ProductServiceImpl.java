package service;

import dao.ProductDAO;
import dto.ProductDTO;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.PaginationBuilder;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

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
    @Transactional
    public void saveOrUpdate(ProductDTO productDTO) {
        Product product = productDAO.getProduct(productDTO.getId());
        if(product == null){
            product = new Product();
        }
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        productDAO.saveOrUpdate(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    @Override
    public int getRandomIdWithoutDisc() {
        return productDAO.getRandomIdWithoutDisc();
    }

}
