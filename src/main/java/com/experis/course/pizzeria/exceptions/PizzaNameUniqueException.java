package com.experis.course.pizzeria.exceptions;

public class PizzaNameUniqueException extends RuntimeException{
    public PizzaNameUniqueException(String message) {
        super(message);
    }
}
