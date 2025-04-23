package com.blog.services;

import java.util.List;

import com.blog.payload.CategoryDto;

public interface CategoryService {
	
	//create
	
	public CategoryDto createCatogery(CategoryDto categoryDto);
	
	//get
	
	public CategoryDto getCategorybyId(Integer catId);
	
	//update
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
	
	//get all
	
	List<CategoryDto> getAll();
	
	//delete
	
	void deleteCategory(Integer catId);

}
