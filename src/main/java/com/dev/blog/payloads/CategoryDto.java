package com.dev.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {

	private Integer categoryId;

	@NotBlank
	@Size(min = 4, message = "Category Title should be more than 4 characters !!")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, message = "Description should not be less than 10 characters !!")
	private String categoryDescription;

	public CategoryDto() {
		super();
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

}
