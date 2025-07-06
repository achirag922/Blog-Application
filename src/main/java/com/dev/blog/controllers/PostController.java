package com.dev.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.blog.constants.AppConstants;
import com.dev.blog.payloads.ApiResponse;
import com.dev.blog.payloads.PostDto;
import com.dev.blog.payloads.PostResponse;
import com.dev.blog.services.FileService;
import com.dev.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.iamge}")
	private String path;

	// Add Post

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	// Get Post by User

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {

		List<PostDto> postsDtos = this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsDtos, HttpStatus.OK);
	}

	// Get Post by Category

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// Update the post

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
		return ResponseEntity.ok(this.postService.getAllPost(pageNumber, pageSize, sortBy));

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		return ResponseEntity.ok(this.postService.getPostByid(postId));
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
		List<PostDto> searchPost = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);

	}

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDto postDto = this.postService.getPostByid(postId);
		String fileName = this.fileService.uploadFile(path, image);

		postDto.setPostImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	@GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
