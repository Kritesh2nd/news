package com.example.school.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class News {
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "sub_title", nullable = true)
	private String subTitle;
	
	@Column(name = "current_date", nullable = false)
    private LocalDateTime currentDate;
	
	@Column(name = "current_temperature", nullable = false)
    private LocalDateTime currentTemperature;

}
