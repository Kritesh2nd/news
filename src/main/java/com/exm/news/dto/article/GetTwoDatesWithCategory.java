package com.exm.news.dto.article;

public class GetTwoDatesWithCategory {
	private String startDate;
	private String endDate;
	private String category;
	
	public GetTwoDatesWithCategory() {}
	
	public GetTwoDatesWithCategory(String startDate, String endDate, String category) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
