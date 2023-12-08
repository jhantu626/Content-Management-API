package io.pritamproduction.blog.service.impl;

import io.pritamproduction.blog.entites.Category;
import io.pritamproduction.blog.exception.ResourceNotFound;
import io.pritamproduction.blog.payloads.CategoryDto;
import io.pritamproduction.blog.repositoris.CategoryRepository;
import io.pritamproduction.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=modelMapper.map(categoryDto,Category.class);
		Category addedCategory=repository.save(category);
		return modelMapper.map(addedCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category=repository.findById(id).orElseThrow(()->new ResourceNotFound("Category","id",id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory=repository.save(category);
		return modelMapper.map(updatedCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category category=repository.findById(id).orElseThrow(()->new ResourceNotFound("Category","Id",id));
		return modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories=repository.findAll();
		List<CategoryDto> categoryDtos=categories.stream().map((cat)->modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category=this.repository.findById(id).orElseThrow(()->new ResourceNotFound("Category","id",id));
		this.repository.delete(category);
	}
}
