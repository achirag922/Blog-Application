package com.dev.blog.services;

import com.dev.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

}
