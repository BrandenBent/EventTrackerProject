package com.skilldistillery.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.event.entities.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {

}
