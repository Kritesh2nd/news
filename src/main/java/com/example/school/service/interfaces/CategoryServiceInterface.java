package com.example.school.service.interfaces;

import java.util.List;

import com.example.school.model.Category;
import com.example.school.response.BasicResponseDto;

public interface CategoryServiceInterface {

//	C[R]UD	
	public List<Category> getCategoryList();

//	[C]RUD
	public BasicResponseDto addCategory(String category);
	public BasicResponseDto addAllCategories(List<String> category);

//	CR[U]D
	public BasicResponseDto updateCategory(Category category);
	
//	CRU[D]
	public BasicResponseDto deleteCategory(Long id);
	public BasicResponseDto deleteCategory(String category);
	public BasicResponseDto deleteAllCategories();
}
