package com.dev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
