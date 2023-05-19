package application.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

	public List<Loan> getLoansByCardNumber(UUID id) {
		TypedQuery<Loan> getAllQuery = em.createNamedQuery("findByCardNumber", Loan.class);
		getAllQuery.setParameter("cardNumber", id);
		return getAllQuery.getResultList();
	}

	public List<Loan> getLoansExpiredAndNotReturned(LocalDate date) {
		TypedQuery<Loan> getAllQuery = em.createNamedQuery("findExpiredLoans", Loan.class);
		getAllQuery.setParameter("currentDate", date);
		return getAllQuery.getResultList();
	}

}
