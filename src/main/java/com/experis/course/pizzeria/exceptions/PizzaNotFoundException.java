package com.experis.course.pizzeria.exceptions;

public class PizzaNotFoundException extends RuntimeException{
    public PizzaNotFoundException(String message) {
        super(message);
    }
}
