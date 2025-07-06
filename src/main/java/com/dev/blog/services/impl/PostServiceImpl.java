package com.dev.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.blog.entities.Category;
import com.dev.blog.entities.Post;
import com.dev.blog.entities.User;
import com.dev.blog.exceptions.ResourceNotFoundException;
import com.dev.blog.payloads.PostDto;
import com.dev.blog.payloads.PostResponse;
import com.dev.blog.repositories.CategoryRepo;
import com.dev.blog.repositories.PostRepo;
import com.dev.blog.repositories.UserRepo;
import com.dev.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImageName(postDto.getPostImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy));

		Page<Post> Pagepost = this.postRepo.findAll(pageable);

		List<Post> allPosts = Pagepost.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(Pagepost.getNumber());
		postResponse.setPageSize(Pagepost.getSize());
		postResponse.setTotalElements(Pagepost.getTotalElements());
		postResponse.setTotalPages(Pagepost.getTotalPages());
		postResponse.setLastPage(Pagepost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostByid(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keywords) {
		List<Post> posts = this.postRepo.findBypostTitleContaining(keywords);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

}
