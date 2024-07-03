package com.exm.news.dto.article;

import com.exm.news.entity.app.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortArticleDto {

	private Long articleId;
	
	private String title;
    
    private String shortContent;
    
    private String imageUrl;

    private User author;

}
