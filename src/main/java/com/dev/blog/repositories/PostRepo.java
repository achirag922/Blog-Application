package com.dev.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.blog.entities.Category;
import com.dev.blog.entities.Post;
import com.dev.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	
	List<Post> findBypostTitleContaining(String postTitle);

}
