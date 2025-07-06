package com.dev.blog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.blog.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	

}
