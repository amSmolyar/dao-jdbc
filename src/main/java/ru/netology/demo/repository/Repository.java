package ru.netology.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Repository {
    private String sqlScript;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private DataSource dataSource;

//    public void setDataSource(DataSource dataSource) {
//        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }

//    public Repository (DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.sqlScript = read("myScript.sql");
//        setDataSource(dataSource);
//    }

    public Repository () {
        this.sqlScript = read("myScript.sql");
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getProductName(String name) {

        List<String> productList = namedParameterJdbcTemplate.query(
                sqlScript,
                new MapSqlParameterSource("name", name),
                new BeanPropertyRowMapper<>(String.class));

        return productList;
    }

}
