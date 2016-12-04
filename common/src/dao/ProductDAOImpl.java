package dao;

import dto.ProductDTO;
import util.PaginationBuilder;
import model.Product;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{

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
                .createSQLQuery("SELECT p.id AS id, p.name AS name, p.price AS price, d.id IS NOT NULL AS discounted FROM product p " +
                        "LEFT OUTER JOIN (SELECT * FROM discount WHERE end_date IS NULL) AS d ON p.id = d.product_id ORDER BY p.id")
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("price", BigDecimalType.INSTANCE)
                .addScalar("discounted", BooleanType.INSTANCE)
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
        return (ProductDTO) getCurrentSession()
                .createSQLQuery("SELECT p.id AS id, p.name AS name, p.price AS price, d.id IS NOT NULL AS discounted FROM product p " +
                        "LEFT OUTER JOIN (SELECT * FROM discount WHERE end_date IS NULL) AS d ON p.id = d.product_id " +
                        "WHERE p.id = :id")
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("price", BigDecimalType.INSTANCE)
                .addScalar("discounted", BooleanType.INSTANCE)
                .setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .uniqueResult();
    }

    @Override
    public Product getProduct(int id) {
        Product product = getCurrentSession().get(Product.class, id);
        if (product != null){
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
    public int getRandomIdWithoutDisc() {
        return (int) getCurrentSession()
                .createQuery("select p.id from Product p " +
                        "where p.id not in (select d.product.id from Discount d where d.endDate is null) " +
                        "order by rand()")
                .setMaxResults(1).uniqueResult();
    }

}
