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

/**
 * @author Florent Callaou
 * Dao persisting with JPA
 */
public class PizzaDaoJPA implements IPizzaDao {

	/** entityManagerFactory : EntityManagerFactory */
	private EntityManagerFactory entityManagerFactory = null;
	/** pizzas : List<Pizza> */
	List<Pizza> pizzas = new ArrayList<>();

	/**
	 * Constructor
	 */
	public PizzaDaoJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
		pizzas = PizzaProvider.provideInitialPizzaList();
		initializeDatabase();
	}

	/**
	 * Initilize the database with a list of pizza
	 */
	private void initializeDatabase(){
		pizzas.forEach(pizza -> {
			try {
				insert(pizza, "select p from Pizza p where p.code='" + pizza.getCode() + "'");
			} catch (SavePizzaException e) {
				//Unimplemented : no need to check if existing when initializing
			}
		});
	}

	/**
	 * Insert an element in database
	 * @param element : the element
	 * @param query : the query verifying if the element already exists
	 * @throws SavePizzaException
	 */
	private <T> void insert(T element, String query) throws SavePizzaException {

		Optional<T> elementFound = findAnythingyBy(query);
		
		if(!elementFound.isPresent()){
			executeTransaction(em -> em.persist(element));
		} else{
			throw new SavePizzaException("This " + element.getClass().getName().toLowerCase() + " already exists");
		}
	}

	
	/**
	 * Find an element by a query
	 * @param query 
	 * @return : the element if found, nullable Optional if not
	 */
	@SuppressWarnings("unchecked")
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

	/**
	 * Execute a transaction with a given action 
	 * @param consumer : the action (insert, update, delete...)
	 */
	public void executeTransaction(Consumer<EntityManager> consumer) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		consumer.accept(entityManager);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
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

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		try{
			insert(pizza, "select p from Pizza p where p.code='" + pizza.getCode() + "'");
		} catch (SavePizzaException e) {
			throw new SavePizzaException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.Integer, fr.pizzeria.model.Pizza)
	 */
	@Override
	public void updatePizza(Integer id, Pizza pizza) throws UpdatePizzaException {
		
		pizza.setId(id);
		
		executeTransaction(em -> em.merge(pizza));
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void deletePizza(Pizza pizza) throws DeletePizzaException {
		executeTransaction(em -> {
			Pizza pizzaDatabase = em.find(Pizza.class, pizza.getId());
			em.remove(pizzaDatabase);
		});
	}
	
	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#close()
	 */
	@Override
	public void close(){
		entityManagerFactory.close();
	}



}
