package com.example.school.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.school.model.Category;
import com.example.school.constant.PathConstant;
import com.example.school.service.CategoryService;
import com.example.school.response.BasicResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping(PathConstant.CATEGORY)
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	
	@GetMapping(PathConstant.CAT)
	public ResponseEntity<String> cat(){
		return new ResponseEntity<String>("Cat Category",HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST)
	public ResponseEntity<List<Category>> userList(){
		return new ResponseEntity<List<Category>>(categoryService.getCategoryList(),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.GET_BY_ID)
	public ResponseEntity<Category> getUserById(@PathVariable Long id){
		return new ResponseEntity<Category>(categoryService.getCategoryById(id),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.ADD)
	public ResponseEntity<BasicResponseDto> addCategory(@RequestBody String category){
		System.out.println("Controller add");
		return new ResponseEntity<BasicResponseDto>(categoryService.addCategory(category),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.ADD_ALL)
	public ResponseEntity<BasicResponseDto> addCategories(@RequestBody List<String> category){
		return new ResponseEntity<BasicResponseDto>(categoryService.addAllCategories(category),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.UPDATE)
	public ResponseEntity<BasicResponseDto> updateUser(@RequestBody Category category){
		return new ResponseEntity<BasicResponseDto>(categoryService.updateCategory(category),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.DELETE_ALL)
	public ResponseEntity<BasicResponseDto> deleteAll(){
		return new ResponseEntity<BasicResponseDto>(categoryService.deleteAllCategories(),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.DELETE_BY_ID)
	public ResponseEntity<BasicResponseDto> deleteById(@PathVariable Long id){
		return new ResponseEntity<BasicResponseDto>(categoryService.deleteCategory(id),HttpStatus.OK);
	}
	
	
}
