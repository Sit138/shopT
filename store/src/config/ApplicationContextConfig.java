package config;

import dao.*;
import dao.users.RoleDAO;
import dao.users.RoleDAOImpl;
import dao.users.UserDAO;
import dao.users.UserDAOImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan({"dao", "service", "controller"})
@EnableTransactionManagement
@EnableWebMvc
@EnableScheduling
@Import({ SecurityConfig.class })
public class ApplicationContextConfig {

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
        sessionFactoryBuilder.scanPackages("entity", "com.scheduler");
        sessionFactoryBuilder.addProperties(getHibernateProperties());
        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Autowired
    SessionFactory sessionFactory;

    @Bean(name = "productDAO")
    public ProductDAO getProductDAO(){
        return new ProductDAOImpl(sessionFactory);
    }

    @Bean(name = "discountDAO")
    public DiscountDAO getDiscountDAO(){
        return new DiscountDAOImpl(sessionFactory);
    }

    @Bean(name = "roleDAO")
    public RoleDAO getRoleDAO(){
        return new RoleDAOImpl(sessionFactory);
    }

    @Bean(name = "userDAO")
    public UserDAO getUserDAO(){
        return new UserDAOImpl(sessionFactory);
    }

    @Bean(name = "buyerDAO")
    public BuyerDAO getBuyerDAO(){
        return new BuyerDAOImpl(sessionFactory);
    }

    @Bean(name = "saleDAO")
    public SaleDAO getSaleDAO(){
        return new SaleDAOImpl(sessionFactory);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipart = new CommonsMultipartResolver();
        multipart.setMaxUploadSize(3 * 1024 * 1024);
        return multipart;
    }

    @Bean
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }

}
