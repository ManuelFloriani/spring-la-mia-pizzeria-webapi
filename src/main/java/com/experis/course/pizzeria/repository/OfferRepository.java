package com.experis.course.pizzeria.repository;

import com.experis.course.pizzeria.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
