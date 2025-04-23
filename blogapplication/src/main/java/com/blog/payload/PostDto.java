package com.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {
	
	private Integer postId;
	
	private String content;
	
	private String title;
	
	private String imageName;
	
	private Date addDate;
	
	private CategoryDto categories;
	
	private UserDto user;
	
	private Set<CommentDto> comment=new HashSet<>();
	
	
	

	
	public Set<CommentDto> getComment() {
		return comment;
	}

	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}

	public Integer getPostId() {
		return postId;
	}
        
	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	
	public CategoryDto getCategories() {
		return categories;
	}

	public void setCategories(CategoryDto categories) {
		this.categories = categories;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PostDto(String content, String title) {
		super();
		this.content = content;
		this.title = title;
	}

	public PostDto() {
		super();
		
	}
	
	

	
	

}
