package com.experis.course.pizzeria.service;

import com.experis.course.pizzeria.model.Offer;
import com.experis.course.pizzeria.repository.OfferRepository;
import com.experis.course.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    OfferRepository offerRepository;

    public Offer createNewOffer(Integer pizzaId) throws PizzaNotFoundException{
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException("Pizza with id " + pizzaId + ))
    }

}
