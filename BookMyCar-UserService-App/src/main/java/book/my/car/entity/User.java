package book.my.car.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

	
	@Id
	private String userId;
	private String fname;
	private String lname;
	private LocalDate dob;
	private String email;
	private String password;
	
	
}
