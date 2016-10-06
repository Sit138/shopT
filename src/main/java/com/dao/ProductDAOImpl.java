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

    @Override
    public void insertEndDateDiscount(int addTypeDiscount, Date endDateDiscount, int productId) {
        getCurrentSession().createSQLQuery(
                "UPDATE discount SET discount_end_date = :endDateDiscount " +
                        "WHERE discount_end_date IS NULL AND add_type = :addTypeDiscount AND product_id = :productId"
        )
        .setParameter("endDateDiscount", endDateDiscount)
        .setParameter("addTypeDiscount", addTypeDiscount)
        .setParameter("productId", productId)
        .executeUpdate();
    }

    @Override
    public int numberItemsDiscountHistory() {
        return (Integer) getCurrentSession()
                .createSQLQuery("SELECT count(d.id) AS countRows FROM discount d")
                .addScalar("countRows", IntegerType.INSTANCE)
                .uniqueResult();
    }

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder) {
        return getCurrentSession()
                .createQuery("select d.value as value, d.startDate as startDate, d.endDate as endDate, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice, d.addType as addType " +
                        "from Discount d left outer join d.product p on p.id=d.product.id order by d.id desc")
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .setFirstResult(paginationBuilder.getNumberFirstSamplingElement())
                .setMaxResults(paginationBuilder.getNumberRowsOnPage())
                .list();
    }

    @Override
    public DiscountDTO getNowDiscountProduct() {

        DiscountDTO discountProductNowDTO = (DiscountDTO) getCurrentSession()
                .createSQLQuery("SELECT d.discount_value AS value, d.discount_start_date AS startDate, d.discount_end_date AS endDate, " +
                        "p.id AS productId, p.product_name AS productName, p.product_price AS productPrice, d.add_type AS addType " +
                        "FROM discount d LEFT OUTER JOIN product p ON p.id=d.product_id " +
                        "WHERE d.discount_end_date is null")
                .addScalar("value", DoubleType.INSTANCE)
                .addScalar("startDate", TimestampType.INSTANCE)
                .addScalar("endDate", TimestampType.INSTANCE)
                .addScalar("productId", IntegerType.INSTANCE)
                .addScalar("productName", StringType.INSTANCE)
                .addScalar("productPrice", BigDecimalType.INSTANCE)
                .addScalar("addType", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class)).uniqueResult();
        if (discountProductNowDTO != null){
            return discountProductNowDTO;
        }
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
