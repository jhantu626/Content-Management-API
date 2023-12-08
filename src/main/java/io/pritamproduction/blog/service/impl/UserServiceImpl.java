package io.pritamproduction.blog.service.impl;

import io.pritamproduction.blog.entites.User;
import io.pritamproduction.blog.exception.ResourceNotFound;
import io.pritamproduction.blog.payloads.UserDto;
import io.pritamproduction.blog.repositoris.UserRepo;
import io.pritamproduction.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	public UserDto createUser(UserDto userDto){
		User user=dtoToUser(userDto);
		User savedUser=this.userRepository.save(user);

		return this.userTodto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=userRepository.save(user);
		UserDto dto=userTodto(user);
		return dto;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","id",id));
		UserDto userDto=userTodto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=userRepository.findAll();
		List<UserDto> userDtos=new ArrayList<>();
		users.forEach(t->{
			userDtos.add(userTodto(t));
		});
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","userId",id));
		userRepository.delete(user);
	}

	private User dtoToUser(UserDto userDto){
		User user=modelMapper.map(userDto,User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	private UserDto userTodto(User user){
		UserDto userDto=modelMapper.map(user,UserDto.class);
		return userDto;
	}

}
