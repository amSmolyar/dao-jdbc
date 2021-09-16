package ru.netology.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.netology.demo.repository.Repository;
import ru.netology.demo.service.Service;

import javax.sql.DataSource;

@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
public class JavaConfig {


    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return new HikariDataSource();
//    }
//
//    @Bean
//    public Repository repository() {
//        return new Repository(dataSource());
//    }

    /*@Bean
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public HikariDataSource dataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean
    public Repository repository() {
        return new Repository(dataSource(dataSourceProperties()));
    }*/


    @Bean
    public Repository repository() {
        return new Repository();
    }


    @Bean
    public Service service() {
        return new Service(repository());
    }

}
