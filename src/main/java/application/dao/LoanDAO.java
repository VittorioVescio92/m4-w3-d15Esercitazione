package application.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import application.entities.Loan;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoanDAO {
	private final EntityManager em;

	public LoanDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Loan l) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(l);
		transaction.commit();
		log.info("Loan saved");
	}

	public Loan getById(UUID id) {
		Loan found = em.find(Loan.class, id);
		return found;
	}

	public void delete(UUID id) {
		Loan found = em.find(Loan.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Loan " + id + " deleted!");
		}
	}
}
