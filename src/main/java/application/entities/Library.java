package application.entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Library")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findByYear", query = "SELECT l FROM Library l WHERE l.pubblicationYear = :year")
@NamedQuery(name = "findByTitleOrString", query = "SELECT l FROM Library l WHERE LOWER(l.title) LIKE CONCAT('%', LOWER(:title), '%')")
public abstract class Library {
	@Id
	@GeneratedValue
	private UUID ISBN;
	private String title;
	private int pubblicationYear;
	private int pagesNumber;
	@OneToMany(mappedBy = "borrowedItem", cascade = CascadeType.ALL)
	private Set<Loan> loans;

	public Library(String title, int pubblicationYear, int pagesNumber) {
		setTitle(title);
		setPubblicationYear(pubblicationYear);
		setPagesNumber(pagesNumber);
	}

	@Override
	public abstract String toString();
}
