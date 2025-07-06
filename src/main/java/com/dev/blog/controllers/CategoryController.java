package com.dev.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.blog.payloads.ApiResponse;
import com.dev.blog.payloads.CategoryDto;
import com.dev.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);

	}

	// update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updatedCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
	}

	// get
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}

	// getAll
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
		return ResponseEntity.ok(this.categoryService.getCategory(catId));
	}

}
