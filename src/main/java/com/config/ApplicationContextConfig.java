package com.config;

import com.dao.*;

import com.dao.users.RoleDAO;
import com.dao.users.RoleDAOImpl;
import com.dao.users.UserDAO;
import com.dao.users.UserDAOImpl;
import com.scheduler.MyTask;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
@Import({ SecurityConfig.class })
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
        dataSource.setUrl("jdbc:postgresql://localhost:5432/testdb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("user");
        dataSource.setPassword("123");
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionFactoryBuilder.scanPackages("com/model/");
        sessionFactoryBuilder.addProperties(getHibernateProperties());
        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Bean(name = "productDAO")
    public ProductDAO getProductDAO(SessionFactory sessionFactory){
        return new ProductDAOImpl(sessionFactory);
    }

    @Bean(name = "saleDAO")
    public SaleDAO getSaleDAO(SessionFactory sessionFactory){
        return new SaleDAOImpl(sessionFactory);
    }

    @Bean(name = "discountDAO")
    public DiscountDAO getDiscountDAO(SessionFactory sessionFactory){
        return new DiscountDAOImpl(sessionFactory);
    }

    @Bean(name = "roleDAO")
    public RoleDAO getRoleDAO(SessionFactory sessionFactory){
        return new RoleDAOImpl(sessionFactory);
    }

    @Bean(name = "userDAO")
    public UserDAO getUserDAO(SessionFactory sessionFactory){
        return new UserDAOImpl(sessionFactory);
    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

}
