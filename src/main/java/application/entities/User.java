package application.entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
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
public class User {
	@Id
	@GeneratedValue
	private UUID cardNumber;
	private String name;
	private String lastname;
	private LocalDate birthDate;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Loan> loans;

	public User(String name, String lastname, LocalDate birthDate) {
		this.name = name;
		this.lastname = lastname;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [numeroTessera=" + cardNumber + ", name=" + name + ", lastname=" + lastname + ", birthDate="
				+ birthDate + "]";
	}

}
