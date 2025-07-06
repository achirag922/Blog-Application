package com.dev.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.blog.entities.Comment;
import com.dev.blog.entities.Post;
import com.dev.blog.exceptions.ResourceNotFoundException;
import com.dev.blog.payloads.CommentDto;
import com.dev.blog.repositories.CommentRepo;
import com.dev.blog.repositories.PostRepo;
import com.dev.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);

		Comment savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDto.class);

	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

		this.commentRepo.delete(comment);
	}

}
