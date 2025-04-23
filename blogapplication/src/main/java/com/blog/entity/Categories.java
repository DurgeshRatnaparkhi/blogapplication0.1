package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categories {
	 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoriId;
	
	@Column(name="title")
	private String categoryTitle;
	
	@Column(name="decription")
	private String categoryDescription;
	
	
	@OneToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
	

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

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categories(Integer categoriId, String categoryTitle, String categoryDescription) {
		super();
		this.categoriId = categoriId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	
	
	
	

}
