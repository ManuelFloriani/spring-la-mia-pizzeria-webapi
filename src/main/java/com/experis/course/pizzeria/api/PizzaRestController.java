package com.experis.course.pizzeria.api;

import com.experis.course.pizzeria.model.Pizza;
import com.experis.course.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;
    // Lista di pizze
    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> search) {
        // ritorna la lista delle pizze dal database
        return pizzaService.getPizzaList(search);
    }
}
