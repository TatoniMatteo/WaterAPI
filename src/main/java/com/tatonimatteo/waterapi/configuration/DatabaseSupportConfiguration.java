package com.tatonimatteo.waterapi.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "supportEntityManagerFactory",
        basePackages = {"com.tatonimatteo.waterapi.repository.support"})
public class DatabaseSupportConfiguration {

    @Value("${spring.datasource.support.url}")
    private String url;
    @Value("${spring.datasource.support.username}")
    private String username;
    @Value("${spring.datasource.support.password}")
    private String password;

    @Bean("support-dataSource")
    public DataSource dataDataSource() {
        return DataSourceBuilder
                .create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }


    @Bean(name = "supportEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("support-dataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.tatonimatteo.waterapi.entity.support")
                .persistenceUnit("support-db")
                .build();
    }

    @Bean(name = "supportTransactionManager")
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("supportEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
