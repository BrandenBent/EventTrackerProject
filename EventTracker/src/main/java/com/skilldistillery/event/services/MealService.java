package com.skilldistillery.event.services;

import java.util.List;

import com.skilldistillery.event.entities.Meal;

public interface MealService {
	List<Meal> showAll();
	Meal showById(Integer id);
	Meal addMeal(Meal meal);
	Meal updateMeal(Integer id, Meal meal);
	boolean deleteMeal(Integer id);

}
