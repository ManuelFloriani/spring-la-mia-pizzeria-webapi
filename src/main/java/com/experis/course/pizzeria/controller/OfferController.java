package com.experis.course.pizzeria.controller;

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
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        try{
            model.addAttribute("offer", offerService.createNewOffer(pizzaId));
            return "offer/create";
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + pizzaId + " not found");
        }
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "offer/create";
        }
        Offer savedOffer = offerService.saveOffer(formOffer);
        return "redirect:/offers/show/" + formOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try{
            Offer offer = offerService.getOffer(id);
            model.addAttribute("offer", offer);
            return "offer/edit";
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "offer with id " + id + " not found");

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
