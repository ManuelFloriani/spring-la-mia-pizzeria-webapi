package com.experis.course.pizzeria.controller;

import com.experis.course.pizzeria.exceptions.PizzaNotFoundException;
import com.experis.course.pizzeria.service.OfferService;
import com.experis.course.pizzeria.model.Offer;
import com.experis.course.pizzeria.model.Pizza;
import com.experis.course.pizzeria.repository.OfferRepository;
import com.experis.course.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    OfferService offerService;
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        try {
            Pizza pizza = pizzaRepository.findById(pizzaId)
                    .orElseThrow(() -> new PizzaNotFoundException("Pizza with id " + pizzaId + " not found"));

            model.addAttribute("pizza", pizza);
            model.addAttribute("offer", offerService.createNewOffer(pizza));
            return "pizza/offers/create";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pizza/offers/create";
        }
        Offer savedOffer = offerService.saveOffer(formOffer);
        return "redirect:/pizza/offers/show/" + formOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try{
            Offer offer = offerService.getOffer(id);
            model.addAttribute("offer", offer);
            return "pizza/offer/edit";
        } catch(PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "offer/edit";
        }

        Offer savedOffer = offerService.saveOffer(formOffer);
        return "redirect:/offers/show/" + formOffer.getPizza().getId();
    }
}
