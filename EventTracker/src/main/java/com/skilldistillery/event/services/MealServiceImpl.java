package com.skilldistillery.event.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.event.entities.Meal;
import com.skilldistillery.event.repositories.MealRepository;

@Service
public class MealServiceImpl implements MealService {
	@Autowired
	private MealRepository repo;

	@Override
	public List<Meal> showAll() {
		return repo.findAll();
	}

	@Override
	public Meal showById(Integer id) {
		Optional<Meal> mealOpt = repo.findById(id);
		Meal meal = new Meal();
		if (mealOpt.isPresent()) {
			meal = mealOpt.get();
		}
		return meal;
	}

	@Override
	public Meal addMeal(Meal meal) {
		return repo.saveAndFlush(meal);
	}

	@Override
	public Meal updateMeal(Integer id, Meal meal) {
		Optional<Meal> mealOpt = repo.findById(id);
		if(mealOpt.isPresent()) {
			Meal mealUpdate = meal;
			mealUpdate.setId(id);
			Meal newMeal = repo.saveAndFlush(mealUpdate);
			return newMeal;
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteMeal(Integer id) {
		Optional<Meal> mealOpt = repo.findById(id);
		if(mealOpt.isPresent()) {
			Meal mealDelete = mealOpt.get();
			repo.delete(mealDelete);
			return true;
		} else {
		return false;
		}
	}

}
