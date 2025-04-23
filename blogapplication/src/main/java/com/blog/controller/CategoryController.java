package com.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.CategoryDto;
import com.blog.services.CategoryService;

@RestController
@RequestMapping("/categ")
public class CategoryController {
	                                             
	@Autowired
	private CategoryService categoryService;
	 
	@PostMapping 
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto catogery = this.categoryService.createCatogery(categoryDto);
		return new ResponseEntity<CategoryDto>(catogery,HttpStatus.CREATED);
	}
	
	@GetMapping("{catId}") 
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		CategoryDto categorybyId = categoryService.getCategorybyId(catId);
		return ResponseEntity.ok(categorybyId);
	} 
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> all = this.categoryService.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCateg(@RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
	{
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
		
		return ResponseEntity.ok(updateCategory);
	}
	
	@DeleteMapping("{catId}")
	public ResponseEntity<Map<String, Boolean>> deleteCateg(@PathVariable Integer catId)
	{
		categoryService.deleteCategory(catId);
		Map<String, Boolean> resp=new HashMap<>();
		resp.put("Deleted successfully", Boolean.TRUE);
		
		return ResponseEntity.ok(resp); 
		
	}      
	 
		

}
