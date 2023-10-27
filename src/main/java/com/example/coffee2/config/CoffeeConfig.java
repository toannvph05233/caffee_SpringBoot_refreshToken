//package com.example.coffee2.config;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.example.coffee2.reponsitory",
//        entityManagerFactoryRef = "vnptcoreappEntityManager",
//        transactionManagerRef = "vnptcoreappTransactionManager")
//@EnableTransactionManagement
//@Log4j2
////@EnableJpaRepositories( entityManagerFactoryRef = "coffeeEntityManager" )
//public class CoffeeConfig {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Bean()
//    public EntityManager entityManager() {
//        return entityManager;
//    }
//}
