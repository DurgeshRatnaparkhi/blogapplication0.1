package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.UserDto;
import com.blog.repository.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	 
	@Autowired    
	private UserRepository userRepository;
 
	
	@Autowired  
	private ModelMapper modelMapper;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		 User user =this.modelMapper.map(userDto, User.class);
		 user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		 User addUser = this.userRepository.save(user);
		return this.modelMapper.map(addUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User cat=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found "+userId));
		cat.setName(userDto.getName());
		cat.setEmail(userDto.getEmail());
		cat.setAge(userDto.getAge());
		cat.setGender(userDto.getGender()); 
		cat.setPassword(userDto.getPassword());
		User updatedUser = userRepository.save(cat);
	
		return this.modelMapper.map(updatedUser, UserDto.class);
	}
	

	@Override
	public UserDto getUserById(Integer userId) {
		User cat=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found "+userId));
		return this.modelMapper.map(cat, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map((cat) -> this.modelMapper.map(cat, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}
   
	
	@Override 
	public void deleteUser(Integer userId) {
		User cat=this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("id not found" +userId));
		this.userRepository.delete(cat);
	}

	
}
