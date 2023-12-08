package io.pritamproduction.blog.controller;

import io.pritamproduction.blog.payloads.ApiResponse;
import io.pritamproduction.blog.payloads.CategoryDto;
import io.pritamproduction.blog.payloads.UserDto;
import io.pritamproduction.blog.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController{
	@Autowired
	private CategoryServiceImpl service;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory=service.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer catId){
		CategoryDto updatedCategory=service.updateCategory(categoryDto,catId);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> Delete(@PathVariable Integer id){
		service.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Succesfully",true),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
		CategoryDto categoryDto=service.getCategory(id);
		return ResponseEntity.ok(categoryDto);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categoryDtos=service.getAllCategory();
		return  ResponseEntity.ok(categoryDtos);
	}

}
