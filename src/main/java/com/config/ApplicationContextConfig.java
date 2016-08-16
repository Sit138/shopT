package com.config;

import com.dao.SaleDAO;
import com.dao.SaleDAOImpl;
import com.model.Sale;
import com.scheduler.MyTask;
import com.dao.ProductDAO;
import com.dao.ProductDAOImpl;
import com.model.Discount;
import com.model.Product;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan("com")
@EnableTransactionManagement
@EnableWebMvc
@EnableScheduling
public class ApplicationContextConfig {

    @Bean
    public MyTask task(){
        return new MyTask();
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/testdb");
        dataSource.setUsername("user");
        dataSource.setPassword("123");
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionFactoryBuilder.addAnnotatedClasses(Product.class, Discount.class, Sale.class);
        sessionFactoryBuilder.addProperties(getHibernateProperties());
        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Autowired
    @Bean(name = "productDAO")
    public ProductDAO getProductDAO(SessionFactory sessionFactory){
        return new ProductDAOImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "saleDAO")
    public SaleDAO geSaleDAO(SessionFactory sessionFactory){
        return new SaleDAOImpl(sessionFactory);
    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

}
