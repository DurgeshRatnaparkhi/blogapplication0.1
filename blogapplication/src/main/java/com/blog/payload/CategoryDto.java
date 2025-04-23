package com.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {
	
	
    private Integer categoriId;
	
	@NotBlank
	@Size(min=4 ,message = "minimun size=4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,message = "min size =10")
	private String categoryDescription;

	public Integer getCategoriId() {
		return categoriId;
	}
                 
	public void setCategoriId(Integer categoriId) {
		this.categoriId = categoriId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(Integer categoriId, String categoryTitle, String categoryDescription) {
		super();
		this.categoriId = categoriId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	
	

}
