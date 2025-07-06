package com.dev.blog.services;

import java.util.List;

import com.dev.blog.payloads.UserDto;

public interface UserService {
	
	
	UserDto createUser (UserDto user);
	UserDto updateUser(UserDto user, Integer userID);
	UserDto getUserById(Integer userID);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userID);

}
