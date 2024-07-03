package com.exm.news.dto.article;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetArticleDto {
	
	@NotBlank(message = "input field cannot be blank")
    private String title;
    
    private String shortContent;
    
    private String imageUrl;
    
	@NotBlank(message = "input field cannot be blank")
    private String content;
    
	@NotBlank(message = "input field cannot be blank")
    private String category;

}
