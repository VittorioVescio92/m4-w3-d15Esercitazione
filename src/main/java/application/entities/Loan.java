package application.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Loans")
@Getter
@Setter
@NoArgsConstructor
public class Loan {
	@Id
	@GeneratedValue
	private UUID id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Library borrowedItem;
	private LocalDate startLoanDate;
	private LocalDate expectedReturnDate;
	private LocalDate returnDate;

	public Loan(User user, Library borrowedItem, LocalDate startLoanDate, LocalDate returnDate) {
		this.user = user;
		this.borrowedItem = borrowedItem;
		this.startLoanDate = startLoanDate;
		this.expectedReturnDate = startLoanDate.plusDays(30);
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "loan [id=" + id + ", user=" + user + ", borrowedItem=" + borrowedItem + ", startLoanDate="
				+ startLoanDate + ", expectedReturnDate=" + expectedReturnDate + ", returnDate=" + returnDate + "]";
	}

}
