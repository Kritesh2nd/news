package com.example.school.service.interfaces;

import java.util.List;

import com.example.school.model.Category;
import com.example.school.model.User;
import com.example.school.response.BasicResponseDto;

public interface CategoryServiceInterface {
/*
 * 
 * C[R]UD
 * get all category
 * get category by id
 * 
 * [C]RUD
 * add category
 * add all categories
 * 
 * CR[U]D
 * update category
 * 
 * CRU[D]
 * delete category
 * delete all category
 * 
 * 
 * */
	
	public List<Category> getCategoryList();
	
	public BasicResponseDto addCategory(String category);
	public BasicResponseDto addAllCategories(List<String> category);
	
	public BasicResponseDto updateCategory(Category category);
	
	public BasicResponseDto deleteCategory(Long id);
	public BasicResponseDto deleteCategory(String category);
	public BasicResponseDto deleteAllCategories();
}
