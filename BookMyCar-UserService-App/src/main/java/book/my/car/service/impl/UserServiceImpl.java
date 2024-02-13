package book.my.car.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import book.my.car.apiresponse.RegisterApiResponse;
import book.my.car.entity.User;
import book.my.car.exception.ResourceNotFoundException;
import book.my.car.payload.UserDto;
import book.my.car.repo.UserRepository;
import book.my.car.service.UserService;
import book.my.car.utils.EmailUtils;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private EmailUtils emailUtils;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public RegisterApiResponse createUser(UserDto userDto) {

		String uid = UUID.randomUUID ().toString ();
		userDto.setUserId (uid);

		RegisterApiResponse aResponse = new RegisterApiResponse ();

		User user2 = new User ();

		BeanUtils.copyProperties (userDto, user2);

		String password = passwordEncoder.encode (user2.getPassword ());
		user2.setPassword (password);


		String fullName = userDto.getFname ();


		System.out.println (fullName);
		String subject = "Your Registration Is Success";
		//String fileName = "REG-EMAIL-BODY.txt";
		String body = readEmailBody (fullName, password);

		emailUtils.sendEmail (userDto.getEmail (), subject, body);

		userRepository.save (user2);

		aResponse.setFname (userDto.getFname ());
		aResponse.setLname (userDto.getLname ());
		aResponse.setDob (userDto.getDob ());
		aResponse.setEmail (userDto.getEmail ());
		aResponse.setUserId (userDto.getUserId ());
		aResponse.setPassword (userDto.getPassword ());


		return aResponse;

	}

	private String readEmailBody(String fullName, String pwd) {

		String url = "";
		String mailBody = null;

		StringBuffer buffer = new StringBuffer();

		try {

			FileReader fr = new FileReader("C:\\Users\\Lenovo\\IdeaProjects\\BookMyCar_V\\BookMyCar-UserService-App\\REG-EMAIL-BODY.txt");

			BufferedReader br = new BufferedReader(fr);		

			String line = br.readLine();
			while (line != null) {
				buffer.append(line);
				line = br.readLine();
			}
			br.close();
			mailBody = buffer.toString();

			mailBody = mailBody.replace("{FULLNAME}", fullName)
					.replace("{TEMP-PWD}", pwd)
					.replace("{URL}", url)
					.replace("{PWD}", pwd);

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
		return mailBody;
	}	
	
	@Override
	public UserDto updateUser(UserDto userDto, String userId) {

		User user2 = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user2.setFname(userDto.getFname());
		user2.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user2.setDob(userDto.getDob());
		user2.setEmail(userDto.getEmail());
		this.userRepository.save(user2);
		BeanUtils.copyProperties(user2, userDto);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> userList = this.userRepository.findAll();
		List<UserDto> userDtos = new ArrayList();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userList, userDtos);
		userList.stream().map(x -> {
			UserDto userDto2 = new UserDto();
			BeanUtils.copyProperties(x, userDto2);
			userDtos.add(userDto2);
			return x;
		}).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserById(String userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		UserDto userDto = new UserDto();
		this.userRepository.save(user);
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public UserDto updateSpecificField(String userId, Map<String, Object> fields) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "uId", userId));
		fields.forEach((key, value) -> {
			Field field = org.springframework.util.ReflectionUtils.findField(User.class, key);
			Optional<Field> optionalField = Optional.ofNullable(field);
			if (optionalField.isPresent()) {
				field.setAccessible(true);
				ReflectionUtils.setField(field, user, value);
			} else {
				// Handle the case where the field is not found.
				// You may want to log a warning or throw an exception.
				System.err.println("Field not found: " + key);
			}
		});
		User savedUser = userRepository.save(user);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(savedUser, userDto);
		return userDto;
	}
}
