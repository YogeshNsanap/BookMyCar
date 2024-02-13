package book.my.car.payload;

import lombok.Data;

@Data
public class LoginDto {

	private String email;

	private String password;
	
	private String token;
}
