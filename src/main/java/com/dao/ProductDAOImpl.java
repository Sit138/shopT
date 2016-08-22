package com.dao;

import com.controller.ProductController;
import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;
import com.model.Sale;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{

    private final Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    private SessionFactory sessionFactory;

    public ProductDAOImpl(){

    }

    public ProductDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ProductDTO> listProducts() {

        List<ProductDTO> productList = sessionFactory.getCurrentSession()
                .createQuery("select p.id as id, p.productName as productName, p.productPrice as productPrice\n" +
                             "from Product p order by id ASC")
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .list();
        if (productList.isEmpty()){
            logger.info("Product list is empty");
        } else {
            logger.info("Adding product list");
        }
        return productList;
    }

    @Override
    public ProductDTO getProductDTO(int id) {
        String hql = "select p.id as id, p.productName as productName, p.productPrice as productPrice\n" +
                     "from Product p where id=" + id;
        List<ProductDTO> productList = sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .list();

        if (productList != null && !productList.isEmpty()){
            logger.info("Return Product to id = " + id);
            return productList.get(0);
        } else {
            logger.info("Product to id=" + id + " null");
        }
        return null;
    }

    @Override
    public Product getProduct(int id) {
        String hql = "from Product p where id=" + id;
        List<Product> productList = sessionFactory.getCurrentSession()
                .createQuery(hql)
                .list();

        if (productList != null && !productList.isEmpty()){
            logger.info("Return Product to id = " + id);
            return productList.get(0);
        } else {
            logger.info("Product to id=" + id + " null");
        }
        return null;
    }

    @Override
    public void saveOrUpdate(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public void deleteProduct(int id) {
        Product productToDelete = new Product();
        productToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(productToDelete);
    }

    @Override
    public ProductDTO getLastProduct() {
        List<ProductDTO> lastProduct = sessionFactory.getCurrentSession()
                .createQuery("select p.id as id, p.productName as productName, p.productPrice as productPrice\n" +
                        "from Product p")
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .list();
        if (lastProduct != null && !lastProduct.isEmpty()){
            return lastProduct.get(lastProduct.size() - 1);
        }
        return null;
    }


    @Override
    public void insertProductDiscount() {

        Product productDiscount = (Product) sessionFactory.getCurrentSession()
                                 .createQuery("select p from Product p order by rand()")
                                 .setMaxResults(1).list().get(0);

        Date currentDate = new Date();
        int min = 5; int max = 15;//нижнее/верхнее значение процентов скидки
        double newDiscount = min + (Math.random() * (max - min) + 1);

        BigDecimal productDiscountPrice = productDiscount.getProductPrice()
                .subtract(productDiscount.getProductPrice()
                        .multiply(new BigDecimal(newDiscount / 100)));

        System.out.println("DISCOUNT PRICE = " + productDiscountPrice);
        BigDecimal discountPriceSpread = productDiscount.getProductPrice().subtract(productDiscountPrice);
        Discount discount = new Discount(newDiscount, currentDate, productDiscountPrice, discountPriceSpread);
        productDiscount.addProductDiscont(discount);
        saveOrUpdate(productDiscount);

    }

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts() {
        return sessionFactory.getCurrentSession()
                .createQuery("select d.discountValue AS discountValue, d.discountDate AS discountDate,\n" +
                        "d.productDiscountPrice AS productDiscountPrice, d.discountPriceSpread AS discountPriceSpread,\n" +
                        "d.product.id AS productId, d.product.productName AS productName, d.product.productPrice AS productPrice\n" +
                        "from Discount d left outer join d.product p on p.id=d.product.id order by d.id asc")
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .list();
    }

    @Override
    public DiscountDTO getNowDiscountProduct() {

        List<DiscountDTO> discountProductNowDTO = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT  d.discount_value AS discountValue, date_trunc('hour', d.discount_date) AS discountDate, \n" +
                        "d.product_discount_price AS productDiscountPrice, d.discount_price_spread AS discountPriceSpread, \n" +
                        "p.id AS productId, p.product_name AS productName, p.product_price AS productPrice\n" +
                        "FROM discount d LEFT OUTER JOIN product p ON p.id=d.product_id \n" +
                        "WHERE date_trunc('hour', d.discount_date) = date_trunc('hour', now())")
                .addScalar("discountValue", new DoubleType())
                .addScalar("discountDate", new TimestampType())
                .addScalar("productDiscountPrice", new BigDecimalType())
                .addScalar("discountPriceSpread", new BigDecimalType())
                .addScalar("productId", new IntegerType())
                .addScalar("productName", new StringType())
                .addScalar("productPrice", new BigDecimalType())
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class)).list();
        if (discountProductNowDTO != null && !discountProductNowDTO.isEmpty()){
            return discountProductNowDTO.get(0);
        }
        return null;
    }

    @Override
    public void insertProductSale(int id) {
        Product productSale = getProduct(id);//продукт на продажу
        Date currentDate = new Date();
        BigDecimal saleAmount = productSale.getProductPrice();
        DiscountDTO discountProduct = getNowDiscountProduct();
        if(discountProduct != null && (id == discountProduct.getProductId())){
            double valueDiscount = discountProduct.getDiscountValue() / 100;
            saleAmount = saleAmount.subtract(saleAmount.multiply(new BigDecimal(valueDiscount)));
        }
        Sale sale = new Sale(saleAmount, currentDate);
        productSale.addProductSale(sale);
        saveOrUpdate(productSale);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
