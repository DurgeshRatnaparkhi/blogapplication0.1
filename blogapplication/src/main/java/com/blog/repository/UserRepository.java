package com.blog.repository;

import com.blog.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	public Optional<User> findByEmail(String email);
	
}
 