package com.exm.news.dto.article;

public class GetDateAndCategory {
	String dateTime;
	String Category;
	
	public GetDateAndCategory() {}
	
	public GetDateAndCategory(String dateTime, String category) {
		super();
		this.dateTime = dateTime;
		Category = category;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}
	
}
