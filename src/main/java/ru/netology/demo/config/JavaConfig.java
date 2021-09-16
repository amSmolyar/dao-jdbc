package ru.netology.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.demo.repository.Repository;
import ru.netology.demo.service.Service;

@Configuration
public class JavaConfig {
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource userDataSource() {
//        return new HikariDataSource();
//    }

    @Bean
    public Repository repository() {
        return new Repository();
    }

    @Bean
    public Service service() {
        return new Service(repository());
    }

}
