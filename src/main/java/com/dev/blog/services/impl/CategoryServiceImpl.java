package com.dev.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.blog.entities.Category;
import com.dev.blog.exceptions.ResourceNotFoundException;
import com.dev.blog.payloads.CategoryDto;
import com.dev.blog.repositories.CategoryRepo;
import com.dev.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);

	}
	
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}


	
	

}
