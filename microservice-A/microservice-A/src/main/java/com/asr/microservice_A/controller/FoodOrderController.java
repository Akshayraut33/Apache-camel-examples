package com.asr.microservice_A.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asr.microservice_A.model.FoodOrder;

@RestController
@RequestMapping("api/food")
public class FoodOrderController {

    private List<FoodOrder> foodOrders = new ArrayList<>();

    public FoodOrderController() {

        foodOrders.add(new FoodOrder(
                1L,
                "Akshay",
                "Pizza",
                2,
                500.00,
                "PENDING"));

        foodOrders.add(new FoodOrder(
                2L,
                "Rohit",
                "Burger",
                1,
                150.00,
                "PENDING"));

        foodOrders.add(new FoodOrder(
                3L,
                "Suresh",
                "Idli",
                3,
                180.00,
                "PROCESSING"));
    }

    @GetMapping("/test")
    public String testApi() {
        return "working";
    }

    @GetMapping("/orders")
    public List<FoodOrder> getAllOrders() {
        return foodOrders;
    }
}
