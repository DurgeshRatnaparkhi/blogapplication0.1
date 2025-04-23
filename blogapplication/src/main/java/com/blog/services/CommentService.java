package com.blog.services;

import com.blog.payload.CommentDto;

public interface CommentService {
	
	//create comment
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);

	//CommentDto createComment(CommentDto commentDto, Integer postId);
	
	
	

}
