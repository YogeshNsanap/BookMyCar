package book.my.car.controller;

import java.util.HashMap;
import java.util.Map;

import book.my.car.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.my.car.jwtToken.JwtHelper;
import book.my.car.jwtToken.JwtResponce;
import book.my.car.payload.LoginDto;
import book.my.car.service.LoginService;



@RestController
@RequestMapping("users")
@CrossOrigin
public class LoginApiController {

	@Autowired
	private LoginService loginService;
	@Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;
    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(LoginApiController.class);

    // step 1
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        request.setToken(token);
        JwtResponce response = JwtResponce.builder()
                .token(token)
                .email(userDetails.getUsername())
              //  .password(userDetails.getPassword())
                .build();
        Map<String, Object> mapResponse=new HashMap<>();
        mapResponse.put("Message", token);
        mapResponse.put("user",userRepository.findByEmail(request.getEmail()).get());
        return new ResponseEntity(mapResponse, HttpStatus.OK);
    }

    // step 2

    private void doAuthenticate(String name, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(name, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
