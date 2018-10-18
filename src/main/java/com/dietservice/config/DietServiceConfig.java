package com.dietservice.config;

import com.dietservice.dao.DishDAO;
import com.dietservice.dao.impl.DishDAOImpl;
import com.dietservice.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DietServiceConfig {

    @Bean
    public DishDAO getDishDAO(@Autowired DishRepository dishRepository){
        return new DishDAOImpl(dishRepository);
    }
//    @Value("${app.datasource.driverClassName}") String driverClassName;
//    @Value("${app.datasource.url}") String url;
//    @Value("${app.datasource.username}") String username;
//    @Value("${app.datasource.password}") String password;
//
//    @Bean(name = "dataSource")
//    public DataSource getDataSource() {
//        DataSource dataSource = DataSourceBuilder
//                .create()
//                .username(username)
//                .password(password)
//                .url(url)
//                .driverClassName(driverClassName)
//                .build();
//        return dataSource;
//    }
//
//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//        sessionBuilder.scanPackages("com.vflux.rbot.services.account.domain");
//        return sessionBuilder.buildSessionFactory();
//    }
//
//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager getTransactionManager(
//            SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
//                sessionFactory);
//        return transactionManager;
//    }
//
//    @Bean
//    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
//        final DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//        return initializer;
//    }
//
//    @Bean
//    public Session getSession(SessionFactory sessionFactory){
//        return sessionFactory.getCurrentSession();
//    }
}
