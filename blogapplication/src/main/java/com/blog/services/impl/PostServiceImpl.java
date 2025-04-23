package com.blog.services.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog.entity.Categories;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.repository.CatRpository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CatRpository catRpository;

	@Override 
	public PostDto createPost(PostDto postDto,Integer userId, Integer catId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id "+ userId));
		
		Categories category = this.catRpository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + catId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategories(category);
		Post newPost = this.postRepository.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class); // Post convert into PostDto
	}

	//get post by single id 
	
	@Override
	public PostDto getIdByPost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post id not found" +postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}
   
	//get all post
	
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> pagePost=this.postRepository.findAll(p);
		List<Post> allPost = pagePost.getContent();
	
	    List<PostDto> postDtos = allPost.stream() 
	                                     .map(post -> this.modelMapper.map(post, PostDto.class))
	                                    .collect(Collectors.toList());
	    
	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(postDtos);
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setTotalElement(pagePost.getTotalElements());
	    postResponse.setTotalPage(pagePost.getTotalPages());
	    postResponse.setLastPage(pagePost.isLast());
	    
	    
	    return postResponse;
	} 

           //update post
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		 Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post id not found "+postId));
		  post.setContent(postDto.getContent());
		  post.setTitle(postDto.getTitle());
		  post.setImageName(postDto.getImageName());
		   
		  Post updatedPost = this.postRepository.save(post);
		 return this.modelMapper.map(updatedPost, PostDto.class);
	}

        //delete post
	@Override 
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post id not found "+postId)); 
		this.postRepository.delete(post);
	}

	// get post by category
	
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Categories cat = this.catRpository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("catregory id not found "+categoryId));
		List<Post> posts = this.postRepository.findByCategories(cat);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
         
	//get post by user
	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user id not found " +userId));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	//search post
	
	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepository.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}
     
	
}
