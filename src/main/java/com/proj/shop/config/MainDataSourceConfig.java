package com.proj.shop.config;



import com.proj.shop.component.MasterSlaveDataSourceKeySelector;
import com.proj.shop.component.RoutingMainDataSource;
import com.proj.shop.property.HikariProperty;
import com.proj.shop.property.MainDataSourceProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = "com.proj.shop.repository",
        transactionManagerRef = "mainTxManager",
        entityManagerFactoryRef = "mainEntityManagerFactory"
)
@Configuration
public class MainDataSourceConfig {
    private final MainDataSourceProperty mainDataSourceProperty;
    private final HikariProperty hikariProperty;

    private DataSource createDataSource(String url, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(hikariProperty.getMaximumPoolSize());
        config.setMinimumIdle(hikariProperty.getMinimumIdle());

        return new HikariDataSource(config);
    }

    @Primary
    @Bean("mainDataSource")
    DataSource dataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        MasterSlaveDataSourceKeySelector masterSlaveDataSourceKeySelector = new MasterSlaveDataSourceKeySelector();

        // master
        DataSource masterDataSource = createDataSource(
                mainDataSourceProperty.getMaster().getUrl(),
                mainDataSourceProperty.getUsername(),
                mainDataSourceProperty.getPassword());
        dataSourceMap.put(mainDataSourceProperty.getMaster().getName(), masterDataSource);
        masterSlaveDataSourceKeySelector.setMasterKey(mainDataSourceProperty.getMaster().getName());

        // slave
        mainDataSourceProperty.getSlaves().forEach(database -> {
            DataSource slaveDataSource = createDataSource(
                    database.getUrl(),
                    mainDataSourceProperty.getUsername(),
                    mainDataSourceProperty.getPassword());
            dataSourceMap.put(database.getName(), slaveDataSource);
            masterSlaveDataSourceKeySelector.addSlaveKey(database.getName());
        });

        AbstractRoutingDataSource routingDataSource = new RoutingMainDataSource(dataSourceMap, masterSlaveDataSourceKeySelector);
        routingDataSource.afterPropertiesSet();

        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Primary
    @Bean(name = "mainEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateJpaVendorAdapter.setShowSql(true);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.format_sql", "true");

        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.proj.shop.model");
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("mainPersistenceUnit");

        return localContainerEntityManagerFactoryBean;
    }

    @Primary
    @Bean(name = "mainTxManager")
    PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return jpaTransactionManager;
    }
}
