package application;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import application.dao.LibraryDAO;
import application.dao.LoanDAO;
import application.dao.UserDAO;
import application.entities.Book;
import application.entities.Library;
import application.entities.Loan;
import application.entities.Magazine;
import application.entities.Periodicity;
import application.entities.User;
import application.utils.JpaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		LibraryDAO ld = new LibraryDAO(em);
		UserDAO ud = new UserDAO(em);
		LoanDAO ld2 = new LoanDAO(em);

		Library book1 = new Book("Libro1", 2010, 100, "MeMedesimo", "Crime");
		Library book2 = new Book("Libro2", 2010, 229, "MeMedesimo", "Horror");
		Library book3 = new Book("Libro3", 2012, 122, "TuTuesimo", "Comedy");
		Library magazine1 = new Magazine("Magazine1", 2020, 133, Periodicity.MENSILE);
		Library magazine2 = new Magazine("Magazine2", 2023, 500, Periodicity.SETTIMANALE);
		Library magazine3 = new Magazine("Magazine3", 2010, 324, Periodicity.SETTIMANALE);
		User user1 = new User("Ajeje", "Brazorf", LocalDate.of(1992, 4, 15));
		User user2 = new User("Aldo", "Baglio", LocalDate.of(1982, 4, 15));
		User user3 = new User("Vittorio", "Vescio", LocalDate.of(1992, 4, 15));
		Loan loan1 = new Loan(user1, book2, LocalDate.of(2023, 2, 28), LocalDate.of(2023, 4, 25));
		Loan loan2 = new Loan(user3, magazine1, LocalDate.of(2023, 3, 15), LocalDate.of(2023, 3, 25));
		Loan loan3 = new Loan(user2, magazine3, LocalDate.of(2023, 3, 15));
		Loan loan4 = new Loan(user2, magazine2, LocalDate.of(2023, 5, 19));

//		ld.save(book1);
//		ld.save(book2);
//		ld.save(book3);
//		ld.save(magazine1);
//		ld.save(magazine2);
//		ld.save(magazine3);
//
//		ud.save(user1);
//		ud.save(user2);
//		ud.save(user3);
//
//		ld2.save(loan1);
//		ld2.save(loan2);
//		ld2.save(loan3);
//		ld2.save(loan4);

		ld.delete(UUID.fromString("c3765d0f-babe-4161-8327-3bb0ab1bc39c"));

		log.info("************************************************");

		Library found = ld.getByISBN(UUID.fromString("50d91aec-e66c-428e-baad-e4ff70a0ed36"));
		if (found != null) {
			log.info("Il risultato per l'ISBN fornito è: " + found.toString());
		} else {
			log.info("Oggetto non presente");
		}

		log.info("************************************************");

		List<Library> found2 = ld.getByYear(2010);
		if (found2.size() > 0) {
			found2.stream().forEach(i -> log.info("Il risultato per l'anno ricercato è: " + i.toString()));
		} else {
			log.info("Nessun oggetto trovato per questo anno");
		}

		log.info("************************************************");

		List<Book> found3 = ld.getByAuthor("MeMedesimo");
		if (found3.size() > 0) {
			found3.stream().forEach(i -> log.info("Il risultato per l'autore ricercato è: " + i.toString()));
		} else {
			log.info("Nessun oggetto trovato per questo autore");
		}

		log.info("************************************************");

		List<Library> found4 = ld.getByTitleOrPartial("lib");
		if (found4.size() > 0) {
			found4.stream().forEach(i -> log.info("Il risultato della ricerca per titolo è: " + i.toString()));
		} else {
			log.info("Nessun oggetto trovato con questo titolo");
		}

		log.info("************************************************");

		List<Loan> found5 = ld2.getLoansByCardNumber(UUID.fromString("09064541-5c6a-4bb8-91de-87e2d772dfbe"));
		if (found5.size() > 0) {
			found5.stream().forEach(i -> log.info("Il risultato della tessera è: " + i.toString()));
		} else {
			log.info("Nessun prestito in corso trovato per questa tessera");
		}

		log.info("************************************************");

		List<Loan> found6 = ld2.getLoansExpiredAndNotReturned(LocalDate.now());
		if (found6.size() > 0) {
			found6.stream().forEach(i -> log.info("I prestiti attualmente attivi sono: " + i.toString()));
		} else {
			log.info("Nessun prestito attualmente attivo");
		}

		em.close();
		emf.close();
	}
}
