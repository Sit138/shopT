package com.dao;

import com.controller.ProductController;
import com.dto.DiscountDTO;
import com.dto.ProductDTO;
import com.model.Discount;
import com.model.Product;
import com.model.Sale;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
    public List<ProductDTO> listProducts() {

        List<ProductDTO> productList = getCurrentSession()
                .createQuery("select p.id as id, p.name as name, p.price as price\n" +
                             "from Product p order by id asc")
                .setResultTransformer(Transformers.aliasToBean(ProductDTO.class))
                .list();
        return productList;
    }

    @Override
    public ProductDTO getProductDTOById(int id) {
        String hql = "select p.id as id, p.name as productName, p.price as productPrice\n" +
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
        Product productToDelete = new Product(id);
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
    public void insertProductDiscount() {

        Product productDiscount = (Product) getCurrentSession()
                                 .createQuery("select p from Product p order by rand()")
                                 .setMaxResults(1).list().get(0);

        Date currentDate = new Date();
        int min = 5; int max = 15;//нижнее/верхнее значение процентов скидки
        double newDiscount = min + (Math.random() * (max - min) + 1);

        BigDecimal productDiscountPrice = productDiscount.getPrice()
                .subtract(productDiscount.getPrice()
                        .multiply(new BigDecimal(newDiscount / 100)));

        System.out.println("DISCOUNT PRICE = " + productDiscountPrice);
        BigDecimal discountPriceSpread = productDiscount.getPrice().subtract(productDiscountPrice);
        Discount discount = new Discount(newDiscount, currentDate, productDiscountPrice, discountPriceSpread);
        productDiscount.addProductDiscont(discount);
        saveOrUpdate(productDiscount);

    }

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts() {
        return getCurrentSession()
                .createQuery("select d.value as value, d.date as date,\n" +
                        "d.productDiscountPrice as productDiscountPrice, d.discountPriceSpread as discountPriceSpread, " +
                        "d.product.id as productId, d.product.name as productName, d.product.price as productPrice " +
                        "from Discount d left outer join d.product p on p.id=d.product.id order by d.id asc")
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class))
                .list();
    }

    @Override
    public DiscountDTO getNowDiscountProduct() {

        DiscountDTO discountProductNowDTO = (DiscountDTO) getCurrentSession()
                .createSQLQuery("SELECT  d.discount_value AS value, date_trunc('hour', d.discount_date) AS date, " +
                        "d.product_discount_price AS productDiscountPrice, d.discount_price_spread AS discountPriceSpread, " +
                        "p.id AS productId, p.product_name AS productName, p.product_price AS productPrice " +
                        "FROM discount d LEFT OUTER JOIN product p ON p.id=d.product_id " +
                        "WHERE date_trunc('hour', d.discount_date) = date_trunc('hour', now())")
                .addScalar("value", DoubleType.INSTANCE)
                .addScalar("date", TimestampType.INSTANCE)
                .addScalar("productDiscountPrice", BigDecimalType.INSTANCE)
                .addScalar("discountPriceSpread", BigDecimalType.INSTANCE)
                .addScalar("productId", IntegerType.INSTANCE)
                .addScalar("productName", StringType.INSTANCE)
                .addScalar("productPrice", BigDecimalType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class)).uniqueResult();
        if (discountProductNowDTO != null){
            return discountProductNowDTO;
        }
        return null;
    }

    @Override
    public void insertProductSale(int id) {
        Product productSale = getProduct(id);//продукт на продажу
        Date currentDate = new Date();
        BigDecimal saleAmount = productSale.getPrice();
        DiscountDTO discountProduct = getNowDiscountProduct();
        if(discountProduct != null && (id == discountProduct.getProductId())){
            double valueDiscount = discountProduct.getValue() / 100;
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
