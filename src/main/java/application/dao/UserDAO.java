package application.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import application.entities.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDAO {
	private final EntityManager em;

	public UserDAO(EntityManager em) {
		this.em = em;
	}

	public void save(User u) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(u);
		transaction.commit();
		log.info("User saved");
	}

	public User getByCardNumber(UUID id) {
		User found = em.find(User.class, id);
		return found;
	}

	public void delete(UUID id) {
		User found = em.find(User.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("User " + id + " eliminato!");
		}
	}
}
