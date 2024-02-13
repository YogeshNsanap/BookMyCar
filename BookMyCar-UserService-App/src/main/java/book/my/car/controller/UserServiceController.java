package book.my.car.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.my.car.apiresponse.RegisterApiResponse;
import book.my.car.payload.UserDto;
import book.my.car.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserServiceController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
	public ResponseEntity<RegisterApiResponse> cretaeUser(@Valid @RequestBody UserDto user){
		
		RegisterApiResponse aResponse = this.userService.createUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(aResponse);
		
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
	
		List<UserDto> allUsers = this.userService.getAllUsers();
		
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}
	
	@GetMapping("/getSingleUser/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable String userId){
		
		UserDto userById = this.userService.getUserById(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(userById);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId){
		
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
		
	}
	
	
	@PatchMapping(value = "/updateSpecificValue/{userId}",consumes = {"multipart/form-data","application/json","application/xml"})
    public ResponseEntity<UserDto> updateSpecificField(@PathVariable ("userId") String uId,@RequestBody Map<String,Object> objectMap){
        System.out.println (objectMap.isEmpty ());
        UserDto updatedUserDto = this.userService.updateSpecificField(uId,objectMap );
        return ResponseEntity.ok(updatedUserDto);
    }
	

	
}
