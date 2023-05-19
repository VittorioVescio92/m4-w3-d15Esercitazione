package application.entities;

import java.util.UUID;

public abstract class Library {
	private UUID ISBN = UUID.randomUUID();
	private String title;
	private int pubblicationYear;
	private int pagesNumber;

	public Library(String title, int pubblicationYear, int pagesNumber) {
		setISBN(ISBN);
		setTitle(title);
		setPubblicationYear(pubblicationYear);
		setPagesNumber(pagesNumber);
	}

	public UUID getISBN() {
		return ISBN;
	}

	public void setISBN(UUID ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPubblicationYear() {
		return pubblicationYear;
	}

	public void setPubblicationYear(int pubblicationYear2) {
		this.pubblicationYear = pubblicationYear2;
	}

	public int getPagesNumber() {
		return pagesNumber;
	}

	public void setPagesNumber(int pagesNumber) {
		this.pagesNumber = pagesNumber;
	}

	@Override
	public abstract String toString();
}
