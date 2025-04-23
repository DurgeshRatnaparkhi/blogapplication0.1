package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Categories;
import com.blog.entity.Post;
import com.blog.entity.User;

@Repository
public interface PostRepository  extends JpaRepository<Post, Integer>
{
	//custome methods 
	
       List<Post> findByUser(User user);
      
      List<Post>findByCategories(Categories categories);
      
      List<Post> findByTitleContaining(String title);
	
}
 