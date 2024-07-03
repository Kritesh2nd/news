package com.exm.news.dto.article;

import java.time.LocalDateTime;
import java.util.List;

import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.entity.app.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleImagesDto {
    private Long articleId;

    private String title;
    
    private String shortContent;
    
    private List<String> images;
    
    private String content;

    private LocalDateTime publicationDate;

    private GeneralUserDto author;

    private Category category;

}
