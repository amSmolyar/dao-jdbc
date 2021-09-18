package ru.netology.demo.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import ru.netology.demo.dao.Order;
import ru.netology.demo.repository.Repository;

import java.util.List;

public class Service {
    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public List<Order> getProductName(String name) {
        return repository.getProductName(name);
    }

    public void setTablesValues() {
        repository.setTablesValues();
    }
}
