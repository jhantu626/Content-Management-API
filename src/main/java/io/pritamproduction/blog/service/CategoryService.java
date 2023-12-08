package io.pritamproduction.blog.service;


import io.pritamproduction.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
	 CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
	CategoryDto getCategory(Integer id);
	 List<CategoryDto> getAllCategory();
	 void deleteCategory(Integer id);

}
