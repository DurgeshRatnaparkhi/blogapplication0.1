package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("comment not found "+postId));
			Comment comment = this.modelMapper.map(commentDto, Comment.class); //change CommentDto Comment 
			comment.setPost(post);
			Comment saveComment = this.commentRepository.save(comment);
			
		return this.modelMapper.map(saveComment, CommentDto.class);
	} 

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment id not found " +commentId));
		this.commentRepository.delete(comment);
	} 

}
