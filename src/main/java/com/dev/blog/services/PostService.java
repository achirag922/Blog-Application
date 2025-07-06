package com.dev.blog.services;

import java.util.List;

import com.dev.blog.payloads.PostDto;
import com.dev.blog.payloads.PostResponse;

public interface PostService {

	// Create

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update

	PostDto updatePost(PostDto postDto, Integer postId);

	// delete

	void deletePost(Integer postId);

	// get all posts

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

	// get single post

	PostDto getPostByid(Integer postId);

	// get all post by category

	List<PostDto> getAllPostByCategory(Integer categoryId);

	// get all post by user

	List<PostDto> getAllPostByUser(Integer userId);

	// search post

	List<PostDto> searchPost(String keywords);

}
