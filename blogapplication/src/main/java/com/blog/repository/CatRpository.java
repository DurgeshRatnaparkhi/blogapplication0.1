package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Categories;


public interface CatRpository extends JpaRepository<Categories, Integer> {
	

}
