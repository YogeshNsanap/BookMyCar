package book.my.car.apiresponse;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegisterApiResponse {

	
	private String userId;
	private String fname;
	private String lname;
	private LocalDate dob;
	private String email;
	private String password;

}
