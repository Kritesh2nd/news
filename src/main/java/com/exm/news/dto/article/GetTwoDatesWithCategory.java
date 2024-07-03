package com.exm.news.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTwoDatesWithCategory {
	private String startDate;
	private String endDate;
	private String category;

}
