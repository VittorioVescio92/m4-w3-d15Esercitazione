package application.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
public class Book extends Library {
	private String author;
	private String genre;

	public Book(String title, int pubblicationYear, int pagesNumber, String author, String genre) {
		super(title, pubblicationYear, pagesNumber);
		setAuthor(author);
		setGenre(genre);
	}

	@Override
	public String toString() {
		return "ISBN code: " + getISBN() + " | Title: " + getTitle() + " | Pubblication year: " + getPubblicationYear()
				+ " | Number of pages: " + getPagesNumber() + " | Author: " + getAuthor() + " | Genre: " + getGenre();
	}

}
