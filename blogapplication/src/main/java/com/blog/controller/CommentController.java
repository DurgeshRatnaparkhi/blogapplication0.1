package com.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.CommentDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto> creaetComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId)
	{
		CommentDto comment = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
	}
	
	
	//delete co0mment
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Map<String, Boolean>> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		
		Map<String, Boolean> resp=new HashMap<>();
		resp.put("Comment Deleted successfully", Boolean.TRUE);
		
		return ResponseEntity.ok(resp);
	}
 
}
