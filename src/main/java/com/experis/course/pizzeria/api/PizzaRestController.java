package com.experis.course.pizzeria.api;

import com.experis.course.pizzeria.exceptions.PizzaNameUniqueException;
import com.experis.course.pizzeria.exceptions.PizzaNotFoundException;
import com.experis.course.pizzeria.model.Pizza;
import com.experis.course.pizzeria.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    // dettaglio di una pizza
    @GetMapping("/{id}")
    public Pizza details(@PathVariable Integer id) {
        // ritorna la pizza con id specificato
        try {
            return pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // creazione di una pizza
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        // crea una nuova pizza nel database
        try {
            return pizzaService.createPizza(pizza);
        } catch (PizzaNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // modifica di una pizza
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        try {
            return pizzaService.editPizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    // eliminazione di una pizza
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Pizza pizzaToDelete = pizzaService.getPizzaById(id);
            pizzaService.deletePizza(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}