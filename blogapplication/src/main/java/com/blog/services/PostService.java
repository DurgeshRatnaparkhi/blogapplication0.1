package com.blog.services;



import java.util.List;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto, Integer userId, Integer catId);
	
	//public PostDto createPost(PostDto postDto);
	
	//get single post
	
	public PostDto  getIdByPost(Integer postId);
	
	//get all post
	 
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
	
	//update post
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete post
	
	void deletePost(Integer postId);
	
	//get all post by category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	
	List<PostDto> getPostByUser(Integer userId);
	
	//search  post
	
	List<PostDto> searchPost(String keyword);

	
	
	 

}
