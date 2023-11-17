package com.experis.course.pizzeria.controller;

import com.experis.course.pizzeria.model.Pizza;
import com.experis.course.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Pizza> pizzaList; // This attribute is used to store the list of pizzas
        if (search.isPresent()) {
            pizzaList = pizzaRepository.findByNameContainingIgnoreCase(search.get()); // This method returns a list of pizzas that match the name passed as a parameter
        } else {
            pizzaList = pizzaRepository.findAll(); // This method returns all the pizzas in the database thanks to the pizzaRepository attribute
        }
        model.addAttribute("pizzaList", pizzaList); // This method adds the pizzas attribute to the model
        return "pizza/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found"))); // This method adds the pizza attribute to the model
        return "pizza/show";
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza()); // This method adds the pizza attribute to the model
        return "pizza/create";
    }

    @PostMapping("/store")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pizza/create";
        }
        Pizza savedPizza = null;
        savedPizza = pizzaRepository.save(formPizza);
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found"))); // This method adds the pizza attribute to the model
        return "pizza/edit";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pizza/edit";
        }
        Pizza savedPizza = null;
        savedPizza = pizzaRepository.save(formPizza);
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }



    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Pizza pizzaToDelete = pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found"));
            pizzaRepository.delete(pizzaToDelete);
            redirectAttributes.addFlashAttribute("message", "Pizza deleted successfully");
            return "redirect:/pizzas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Pizza could not be deleted");
            return "redirect:/pizzas/show/" + id;
        }
    }

}
