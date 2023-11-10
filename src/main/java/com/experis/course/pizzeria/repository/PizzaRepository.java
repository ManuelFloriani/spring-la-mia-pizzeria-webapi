package com.experis.course.pizzeria.repository;

import com.experis.course.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
    // Here you can define custom queries
    List<Pizza> findByNameContainingIgnoreCase(String nameKeyword); // This method returns a list of pizzas that match the name passed as a parameter
}
