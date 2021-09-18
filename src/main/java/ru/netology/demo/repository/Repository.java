package ru.netology.demo.repository;

import lombok.Data;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.netology.demo.dao.Customer;
import ru.netology.demo.dao.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Repository {
    private String sqlScript;

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Order> getProductName(String name) {
        var query =  entityManager.createQuery(sqlScript, Order.class);
        query.setParameter("name", name);
        return query.getResultList();
//        List<Order> orders = query.getResultList();
//        return orders.stream()
//                .map(Order::getProductName)
//                .collect(Collectors.toList());
    }

    @Transactional
    public void setTablesValues() {
        Random random = new Random();
        Date date = new Date();

        var names = Arrays.asList("Evgeniy", "Pavel", "Olga", "Mihail", "Stepan", "Alexey", "Ivan");
        var surnames = Arrays.asList("Smith", "Black", "White", "Brown", "Green", "Red");
        var products = Arrays.asList("bread", "milk", "eggs", "socks-42", "socks-43", "champagne");

        IntStream.range(0, 5)
                .forEach(i -> {
                    var customer = Customer.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .surname(surnames.get(random.nextInt(surnames.size())))
                            .age(random.nextInt(100))
                            .phoneNumber("+79" + random.nextInt(999999999))
                            .build();

                    this.entityManager.persist(customer);
                });

        IntStream.range(0, 10)
                .forEach(i -> {
                    var order = Order.builder()
                            .date(date)
                            .productName(products.get(random.nextInt(products.size())))
                            .amount(random.nextInt(4) + 1)
                            .customerId(random.nextInt(4) + 1)
                            .build();

                    this.entityManager.persist(order);
                });
    }

}
