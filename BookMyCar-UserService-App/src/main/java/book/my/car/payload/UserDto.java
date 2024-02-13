package book.my.car.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String userId;
	private String fname;
	private String lname;
	private LocalDate dob;
	private String email;
	private String password;
}
