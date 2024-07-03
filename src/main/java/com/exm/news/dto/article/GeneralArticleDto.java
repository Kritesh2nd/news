package com.exm.news.dto.article;

import java.time.LocalDateTime;

import com.exm.news.entity.app.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralArticleDto {

	private Long articleId;
	
	private String title;
    
    private String content;
    
    private String imageUrl;

    private User author;
    
    private LocalDateTime publicationDate;

}
