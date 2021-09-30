package ru.netology.demo.service;
import ru.netology.demo.repository.Repository;

import java.util.List;

public class Service {
    private Repository repository;

    private static int cntInitTableRequest = 0;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public List<String> getProductName(String name) {
        return repository.getProductName(name);
    }

    public String setTablesValues() {
        cntInitTableRequest++;
        if (cntInitTableRequest > 1) {
            return "Tables values have been already set";
        } else {
            repository.setTablesValues();
            return "Tables values have been successfully set";
        }
    }
}
