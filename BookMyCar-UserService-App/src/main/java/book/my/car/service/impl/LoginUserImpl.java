package book.my.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import book.my.car.apiresponse.LoginApiResponse;
import book.my.car.entity.User;
import book.my.car.exception.ResourceNotFoundException;
import book.my.car.payload.LoginDto;
import book.my.car.repo.UserRepository;
import book.my.car.service.LoginService;



@Service
public class LoginUserImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public LoginApiResponse loginUser(LoginDto loginDto) {

		LoginApiResponse lar = new LoginApiResponse();

		String emailId = loginDto.getEmail();
		String password = loginDto.getPassword();

		System.out.println(emailId);
		System.out.println(password);


		User user = userRepository.findByEmail(emailId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", emailId));
		
	//	User user = findByEmail.get();
		System.out.println(user.getFname());
		
		if (user.getEmail().equals(emailId) && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {

			lar.setFname(user.getFname());
			lar.setLname(user.getLname());
			lar.setDob(user.getDob());
			lar.setEmail(user.getEmail());
			lar.setUserId(user.getUserId());
			
		//	lar.setUserName(user.getUserName());

			return lar;

		}

        lar.setMsg("Invalid User Name and Password");

        return lar;

    }



}
