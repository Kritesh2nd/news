package com.exm.news.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "db2EntityManager",
        basePackages = "com.exm.news.repository.app",
        transactionManagerRef = "db2TransactionManager"
)
public class DB2Config {
    
	   @Bean("primaryDataSourceProperties")
	    @ConfigurationProperties("spring.app.datasource")
	    DataSourceProperties primaryDataSourceProperties() {
	        return new DataSourceProperties();
	    }

	    @Bean("db2DataSource")
	    @ConfigurationProperties("spring.app.datasource")
	    public DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties primaryDataSourceProperties) {
	        return primaryDataSourceProperties
	                .initializeDataSourceBuilder()
	                .build();
	    }
    

    
    @Bean("db2EntityManager")
    LocalContainerEntityManagerFactoryBean db2EntityManager(@Qualifier("db2DataSource") DataSource dataSource, EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        Map<String, Object> jpaProperties = new HashMap<>();
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.exm.news.entity.app")
                .persistenceUnit("users")
                .properties(jpaProperties)
                .build();
    }


    @Bean("db2TransactionManager")
    PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManager")
                                                              LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }


}