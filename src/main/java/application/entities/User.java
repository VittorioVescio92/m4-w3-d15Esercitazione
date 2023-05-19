package application.entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
//@NamedQuery(name = "findInPrestito", query = "SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber AND l.returnDate IS NULL")
public class User {
	@Id
	@GeneratedValue
	private UUID numeroTessera;
	private String name;
	private String lastname;
	private LocalDate birthDate;
	@OneToMany(mappedBy = "user")
	private Set<Loan> loans;

	public User(String name, String lastname, LocalDate birthDate) {
		this.name = name;
		this.lastname = lastname;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [numeroTessera=" + numeroTessera + ", name=" + name + ", lastname=" + lastname + ", birthDate="
				+ birthDate + "]";
	}

}
