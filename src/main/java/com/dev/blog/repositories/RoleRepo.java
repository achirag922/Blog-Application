package com.dev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
