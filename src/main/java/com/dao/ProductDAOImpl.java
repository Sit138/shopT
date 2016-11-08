package com.dao;

import com.controller.ProductController;
import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.dto.util.PaginationBuilder;
import com.model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{

    private final Logger logger = Logger.getLogger(ProductController.class);

    private SessionFactory sessionFactory;

    public ProductDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<ProductDTO> listProducts(PaginationBuilder paginationBuilder) {

        List<ProductDTO> productList = getCurrentSession()
                // TODO: Kirill \n, имхо, делает хуже
                .createQuery("select p.id as id, p.name as name, p.price as price\n" +
                             "from Product p order by id asc")
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .setFirstResult(paginationBuilder.getNumberFirstSamplingElement())
                .setMaxResults(paginationBuilder.getNumberRowsOnPage())
                .list();
        return productList;
    }

    @Override
    public int getNumberAllRowsProduct() {
        return (Integer) getCurrentSession()
                .createSQLQuery("SELECT COUNT(p.id) AS countRows FROM product p")
                .addScalar("countRows", IntegerType.INSTANCE)
                .uniqueResult();
    }

    @Override
    public ProductDTO getProductDTOById(int id) {
        String hql = "select p.id as id, p.name as name, p.price as price\n" +
                     "from Product p where id=" + id;
        ProductDTO productDto = (ProductDTO) getCurrentSession()
                .createQuery(hql)
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .uniqueResult();

        // TODO: Kirill бред же?
        if (productDto != null){
            return productDto;
        } else {
            return null;
        }
    }

    @Override
    public Product getProduct(int id) {
        String hql = "from Product p where id=" + id;
        Product product = (Product) getCurrentSession()
                .createQuery(hql)
                .uniqueResult();

        if (product != null){
            Hibernate.initialize(product.getSales());
            Hibernate.initialize(product.getDiscounts());
            return product;
        } else {
            return null;
        }
    }

    @Override
    public void saveOrUpdate(Product product) {
        getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public void deleteProduct(int id) {
        Product productToDelete = getProduct(id);
        getCurrentSession().delete(productToDelete);
    }

    @Override
    public ProductDTO getLastProduct() {
        ProductDTO lastProduct = (ProductDTO) getCurrentSession()
                .createQuery("select p.id as id, p.name as name, p.price as price\n" +
                        "from Product p where id = (select max(id) from p)")
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .uniqueResult();
        if (lastProduct != null){
            return lastProduct;
        }
        return null;
    }

    @Override
    public Product getRandomProduct() {
        return (Product) getCurrentSession()
                .createQuery("select p from Product p order by rand()")
                .setMaxResults(1).uniqueResult();
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
