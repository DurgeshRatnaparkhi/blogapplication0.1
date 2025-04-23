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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.AppConstants;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.payload.UserDto;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<PostDto> createUser(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer catId)
	{
	       PostDto newPost = this.postService.createPost(postDto, userId, catId);
	        
	        return new ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
	}
	
	//update post
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId)
	{
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatePost);
	}
	
	
	
	//getpost by id
	
	@GetMapping("{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto post = this.postService.getIdByPost(postId);
		return ResponseEntity.ok(post);
	}
	
	//get all post
	
	@GetMapping("posts")
	public ResponseEntity<PostResponse> getAllPost1(
	        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
	        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
	        @RequestParam(value = "SortBy",defaultValue = AppConstants.SORT_BY ,required = false) String sortBy
	        ) {
		
	     PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize,sortBy);
	    return ResponseEntity.ok(postResponse);
	}

	
	
	
	  //get by user
	
		@GetMapping("/user/{userId}")
		public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
		{
			List<PostDto> postByUser = this.postService.getPostByUser(userId);
			return ResponseEntity.ok(postByUser);
					
		}
		
		//get by category
		
		@GetMapping("/category/{categoryId}")
		public ResponseEntity<List<PostDto>> getPostByCategosy(@PathVariable Integer categoryId)
		{
			List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
			return ResponseEntity.ok(postByCategory);
		}
		
		
		//delete by post
		
		@DeleteMapping("/posts/{postId}")
		public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Integer postId)
		{
			this.postService.deletePost(postId);
			Map<String, Boolean> resp=new HashMap<>();
			resp.put("Deleted successfully", Boolean.TRUE);
			return ResponseEntity.ok(resp);
			
		}
		
		//search post
		
		@GetMapping("search/{keyword}")
		public ResponseEntity<List<PostDto>> serchPost(@PathVariable String keyword)
		{
			List<PostDto> result = this.postService.searchPost(keyword);
			return ResponseEntity.ok(result);
			
		}
		
	

}	
