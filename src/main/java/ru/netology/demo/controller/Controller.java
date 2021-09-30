package ru.netology.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.demo.service.Service;

import java.util.List;

@RestController
public class Controller {

    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/products/fetch-product")
    public List<String> getProductName(@RequestParam("name") String name) {
        return service.getProductName(name);
    }

    @GetMapping("/products")
    public String setTablesValues() {
        return service.setTablesValues();
    }

}
