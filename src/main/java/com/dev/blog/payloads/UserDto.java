package com.dev.blog.payloads;

import java.util.HashSet;
import java.util.Set;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username should be more than 4 characters !!")
	private String name;

	@Email
	private String email;

	@NotEmpty
	@Size(min = 4, max = 10, message = "Password should be more than 4 and less than 10 characters !!")
	private String password;

	private Set<RoleDto> roles = new HashSet<>();


	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	@NotEmpty
	private String about;

	public UserDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
