package io.pritamproduction.blog.controller;

import io.pritamproduction.blog.payloads.ApiResponse;
import io.pritamproduction.blog.payloads.UserDto;
import io.pritamproduction.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	//createUser

	@Operation(summary = "This api creates new user")
	@PostMapping("/addUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		String password=userDto.getPassword();
		userDto.setPassword(passwordEncoder.encode(password));
		UserDto user=service.createUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	//updateUser
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
		UserDto user=service.updateUser(userDto,userId);
		return ResponseEntity.ok(user);
	}
	//getUser
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> users=service.getAllUser();
		return ResponseEntity.ok(users);
	}
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
		UserDto user=service.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	//deleteUser
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		service.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
}
