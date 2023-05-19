package application.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import application.entities.Book;
import application.entities.Library;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LibraryDAO {
	private final EntityManager em;

	public LibraryDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Library l) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(l);
		transaction.commit();
		log.info("Library Item saved");
	}

	public Library getByISBN(UUID id) {
		Library found = em.find(Library.class, id);
		return found;
	}

	public void delete(UUID id) {
		Library found = em.find(Library.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Library Item with id " + id + " deleted!");
		}
	}

	public List<Library> getByYear(int year) {
		TypedQuery<Library> getAllQuery = em.createNamedQuery("findByYear", Library.class);
		getAllQuery.setParameter("year", year);
		return getAllQuery.getResultList();
	}

	public List<Book> getByAuthor(String author) {
		TypedQuery<Book> getAllQuery = em.createNamedQuery("findByAuthor", Book.class);
		getAllQuery.setParameter("author", author);
		return getAllQuery.getResultList();
	}

	public List<Library> getByTitleOrPartial(String title) {
		TypedQuery<Library> getAllQuery = em.createNamedQuery("findByTitleOrString", Library.class);
		getAllQuery.setParameter("title", title);
		return getAllQuery.getResultList();
	}
}
