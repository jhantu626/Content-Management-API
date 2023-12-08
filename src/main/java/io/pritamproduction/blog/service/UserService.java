package io.pritamproduction.blog.service;

import io.pritamproduction.blog.entites.User;
import io.pritamproduction.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
}
