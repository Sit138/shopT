package dao;

import dto.ProductDTO;
import entity.Product;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import util.Pagination;
import java.util.List;

@Repository(value = "productDAO")
public class ProductDAOImpl extends GeneralDAOImpl<Product> implements ProductDAO{

    //private SessionFactory sessionFactory;

    /*public ProductDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }*/

    /*private Session getSession() {
        return sessionFactory.getCurrentSession();
    }*/

    @Override
    public List<ProductDTO> listProducts(Pagination pagination) {
        return getSession()
                .createSQLQuery("SELECT p.id AS id, p.name AS name, p.price AS price, d.id IS NOT NULL AS discounted FROM product p " +
                        "LEFT OUTER JOIN (SELECT * FROM discount WHERE end_at IS NULL) AS d ON p.id = d.product_id ORDER BY p.id")
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("price", BigDecimalType.INSTANCE)
                .addScalar("discounted", BooleanType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .setFirstResult(pagination.getNumberFirstSamplingElement())
                .setMaxResults(pagination.getNumberRowsOnPage())
                .list();
    }

    @Override
    public int getNumberAllRowsProduct() {
        return Math.toIntExact((Long) getSession()
                .createCriteria(Product.class)
                .setProjection(Projections.rowCount())
                .uniqueResult());
    }

    @Override
    public Product getProduct(int id) {// TODO: Kirill что-то надо изменить в этом методе ++
        Product product = getSession().get(Product.class, id);
        return product;
    }

    @Override
    public void saveOrUpdate(Product product) {
        getSession().saveOrUpdate(product);
    }

    @Override
    public void deleteProduct(int id) {
        getSession().createQuery("delete from Product where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public int getRandomIdWithoutDisc() {
        return (int) getSession()
                .createQuery("select p.id from Product p " +
                        "where p.id not in (select d.product.id from Discount d where d.endAt is null) " +
                        "order by rand()")
                .setMaxResults(1).uniqueResult();
    }

}
