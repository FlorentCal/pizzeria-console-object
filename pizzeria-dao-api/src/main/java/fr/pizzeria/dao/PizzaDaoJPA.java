package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.init.PizzaProvider;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJPA implements IPizzaDao {

	private EntityManagerFactory entityManagerFactory = null;
	List<Pizza> pizzas = new ArrayList<>();

	public PizzaDaoJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
		pizzas = PizzaProvider.provideInitialPizzaList();
		initializeDatabase();
	}

	private void initializeDatabase(){

		pizzas.forEach(pizza -> {
			try {
				insert(pizza, "select p from Pizza p where p.code='" + pizza.getCode() + "'");
			} catch (SavePizzaException e) {
				//Unimplemented : no need to check if existing when initializing
			}
		});
	}

	private <T> void insert(T element, String query) throws SavePizzaException {

		Optional<T> elementFound = (Optional<T>) findAnythingyBy(query);
		
		if(!elementFound.isPresent()){
			executeTransaction(em -> em.persist(element));
		} else{
			throw new SavePizzaException("This pizza already exist ");
		}
	}

	
	public <T extends Object> Optional<T> findAnythingyBy(String query) {
		T anything = null;
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query queryTest = entityManager.createQuery(query);
		if(!queryTest.getResultList().isEmpty()){
			anything = (T) queryTest.getResultList().get(0);
		}	
		
		entityManager.close();

		return Optional.ofNullable(anything);
	}

	public void executeTransaction(Consumer<EntityManager> consumer) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		consumer.accept(entityManager);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<Pizza> findAllPizzas() {
		pizzas = new ArrayList<>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();	
		entityManager.getTransaction().begin();	
		TypedQuery<Pizza> query = entityManager.createQuery("SELECT p FROM Pizza p", Pizza.class);	
		pizzas = query.getResultList();		
		entityManager.close();
		
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		try{
			insert(pizza, "select p from Pizza p where p.code='" + pizza.getCode() + "'");
		} catch (SavePizzaException e) {
			throw new SavePizzaException(e.getMessage());
		}
	}

	@Override
	public void updatePizza(Integer id, Pizza pizza) throws UpdatePizzaException {
		
		pizza.setId(id);
		
		executeTransaction(em -> em.merge(pizza));
	}

	@Override
	public void deletePizza(Pizza pizza) throws DeletePizzaException {
		executeTransaction(em -> {
			Pizza pizzaDatabase = em.find(Pizza.class, pizza.getId());
			em.remove(pizzaDatabase);
		});
	}
	
	@Override
	public void close(){
		entityManagerFactory.close();
	}



}
