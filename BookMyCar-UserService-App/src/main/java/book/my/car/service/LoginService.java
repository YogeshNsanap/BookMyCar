package book.my.car.service;

import book.my.car.apiresponse.LoginApiResponse;
import book.my.car.payload.LoginDto;

public interface LoginService {

	public LoginApiResponse loginUser(LoginDto loginDto);
	
}
