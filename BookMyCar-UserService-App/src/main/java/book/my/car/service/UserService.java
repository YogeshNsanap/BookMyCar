package book.my.car.service;

import java.util.List;
import java.util.Map;

import book.my.car.apiresponse.RegisterApiResponse;
import book.my.car.payload.UserDto;



public interface UserService {

	// create User
	
	public RegisterApiResponse createUser(UserDto user);
	
	// update User
	
	public UserDto updateUser(UserDto userDto,String userId);
	
	// getALlUser
	
	public List<UserDto> getAllUsers();
	
	
	public UserDto getUserById(String userId);
	
	public UserDto updateSpecificField(String userId, Map<String, Object> fields);
	

	
	
}
