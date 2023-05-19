package application.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Magazine extends Library {
	@Enumerated(EnumType.STRING)
	private Periodicity periodicity;

	public Magazine(String title, int pubblicationYear, int pagesNumber, Periodicity periodicity) {
		super(title, pubblicationYear, pagesNumber);
		setPeriodicity(periodicity);
	}

	@Override
	public String toString() {
		return "ISBN code: " + getISBN() + " | Title: " + getTitle() + " | Pubblication year: " + getPubblicationYear()
				+ " | Number of pages: " + getPagesNumber() + " | Periodicity: " + getPeriodicity();
	}

}
