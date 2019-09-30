package com.skilldistillery.event.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.event.entities.Meal;
import com.skilldistillery.event.services.MealService;

@RequestMapping("api")
@RestController
public class MealController {
	@Autowired
	MealService svc;

	@GetMapping("ping")
	public String ping() {
		return "pong\n";
	}
	
	@GetMapping("meal")
	public List<Meal> index(HttpServletRequest req, HttpServletResponse resp){
		try {
			List<Meal> meals = svc.showAll();
			if (meals == null) {
				resp.setStatus(404);
			} else {
				resp.setStatus(200);
			}
			return meals;
		} catch (Exception e) {
			resp.setStatus(404);
			e.printStackTrace();
			return null;
		}
		
	}
	
	@GetMapping("meal/{id}")
	public Meal showById(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			StringBuffer url = req.getRequestURL();
			Meal meal = svc.showById(id);
			if (meal.getId() == 0) {
				url.append("/NOTFOUND");
				resp.setHeader("Location",  url.toString());
				resp.setStatus(404);
				return null;
			} else {
				resp.setStatus(200);
				return meal;
			}
		} catch (Exception e) {
			resp.setStatus(404);
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("meal")
	public Meal addMeal(@RequestBody Meal meal, HttpServletRequest req, HttpServletResponse resp) {
		try {
			Meal newMeal = new Meal();
			newMeal = meal;
			svc.addMeal(newMeal);
			StringBuffer url = req.getRequestURL();
			url.append(newMeal.getId());
			System.err.println(url);
			resp.setStatus(201);
			resp.setHeader("Location", url.toString());
			return newMeal;
		} catch (Exception e) {
			resp.setStatus(404);
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("meal/{id}")
	public Meal updateMeal(@PathVariable Integer id, @RequestBody Meal meal, HttpServletRequest req, HttpServletResponse resp) {
		try {
			Meal updateMeal = svc.updateMeal(id, meal);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(updateMeal.getId());
			resp.setStatus(200);
			resp.setHeader("Location", url.toString());
			return updateMeal;
		} catch (Exception e) {
			resp.setStatus(404);
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("meal/{id}")
	public boolean deleteMeal(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			StringBuffer url = req.getRequestURL();
			svc.deleteMeal(id);
			url.append("/mealDeleted");
			resp.setHeader("Location", url.toString());
			resp.setStatus(204);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			StringBuffer url = req.getRequestURL();
			svc.deleteMeal(id);
			url.append("/deletionFailed");
			resp.setHeader("Location", url.toString());
			resp.setStatus(404);
			return false;
		}
	}
}
