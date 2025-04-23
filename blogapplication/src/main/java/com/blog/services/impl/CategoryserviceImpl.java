package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.entity.Categories;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.repository.CatRpository;
import com.blog.services.CategoryService;

@Service
public class CategoryserviceImpl implements CategoryService {
	
	@Autowired
	private CatRpository catRpository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCatogery(CategoryDto categoryDto) {
		Categories cat = this.modelMapper.map(categoryDto, Categories.class);
		Categories save = this.catRpository.save(cat);
		return this.modelMapper.map(save, CategoryDto.class);
	}
	

	@Override
	public CategoryDto getCategorybyId(Integer catId) {
		Categories cat = this.catRpository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category not found "+catId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}
	

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
		Categories newid = this.catRpository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("categoty not found " +catId));
		newid.setCategoryDescription(categoryDto.getCategoryDescription());
		newid.setCategoryTitle(categoryDto.getCategoryTitle());
		Categories updateId = this.catRpository.save(newid);
		return this.modelMapper.map(updateId, CategoryDto.class);
	}
	
	
	@Override 
	public List<CategoryDto> getAll() { 
	    // Fetch all categories from the repository
	    List<Categories> categories = this.catRpository.findAll();

	    // Map each Categories entity to a CategoryDto using ModelMapper
	    List<CategoryDto> categoryDtos = categories.stream()
	            .map(category -> this.modelMapper.map(category, CategoryDto.class))
	            .collect(Collectors.toList());

	    return categoryDtos;
	}


	@Override
	public void deleteCategory(Integer catId) {
		Categories delteId = this.catRpository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category not found" +catId));
		this.catRpository.delete(delteId);
		
		
	}
	
	
	

}
