package com.skilldistillery.event.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MealTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Meal thing;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("EventPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		thing = em.find(Meal.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		thing = null;
	}
	@Test
	void test() {
		assertEquals(1, thing.getId());
		assertEquals("Banana", thing.getName());
	}

}
