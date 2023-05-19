package application;

import java.time.LocalDate;

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

		em.close();
		emf.close();
	}
}
