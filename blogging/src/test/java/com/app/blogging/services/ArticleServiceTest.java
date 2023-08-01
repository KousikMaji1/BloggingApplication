package com.app.blogging.services;

import com.app.blogging.exceptions.ResourceNotFoundException;
import com.app.blogging.model.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    @DisplayName("Testing create Article")
    void testPost() {
        String [] type= {"java,Spring"};
        Article article = new Article();
        article.setType("Technical");
        article.setName("kousik");
        article.setTags(type);
        article.setAuthorId(123);
        article.setLikes(10);
        article.setVisits(10);
        article.setDuration("20 min");
        System.out.println(article.toString());
        Article ar = articleService.createOrUpdateArticle(article);
        assertEquals(article, ar,"Article should be created");

    }

    @Test
    @DisplayName("Testing Get Article By Id")
    void testGetArticleById(){
        String [] type= {"java,Spring"};
        Article article = new Article();
        //article.setId(1);
        article.setType("Technical");
        article.setName("kousik");
        article.setTags(type);
        article.setAuthorId(123);
        article.setLikes(10);
        article.setVisits(10);
        article.setDuration("20 min");

        Article articleCreated = articleService.createOrUpdateArticle(article);

        assertAll(()->assertEquals(articleCreated.getName(),articleService.getArticleById(articleCreated.getId()).getName()),
                ()->assertEquals(articleCreated.getDuration(),articleService.getArticleById(articleCreated.getId()).getDuration()),
                ()->assertEquals(articleCreated.getType(),articleService.getArticleById(articleCreated.getId()).getType()),
                ()->assertEquals(articleCreated.getLikes(),articleService.getArticleById(articleCreated.getId()).getLikes()),
                ()->assertEquals(articleCreated.getId(),articleService.getArticleById(articleCreated.getId()).getId())
                );

    }

    @Test
    @DisplayName("Testing update Article service")
    void testUpdateById(){
        //create
        String [] type= {"java,Spring"};
        Article article = new Article();
        article.setType("Technical");
        article.setName("kousik");
        article.setTags(type);
        article.setAuthorId(123);
        article.setLikes(10);
        article.setVisits(10);
        article.setDuration("20 min");

        Article articleCreated = articleService.createOrUpdateArticle(article);

        //update
        Article articleRequest= new Article();
        articleRequest.setLikes(20);
        articleRequest.setName("Sami");

        Article articleUpdated = articleService.updateById(articleRequest,articleCreated.getId());

        assertAll(()->assertEquals(articleRequest.getName(),articleUpdated.getName()),
                ()->assertEquals(articleCreated.getDuration(),articleUpdated.getDuration()),
                ()->assertEquals(articleCreated.getType(),articleUpdated.getType()),
                ()->assertEquals(articleRequest.getLikes(),articleUpdated.getLikes()),
                ()->assertEquals(articleCreated.getId(),articleUpdated.getId())
        );

    }

    @Test
    @DisplayName("Testing delete Article service")
    void testDeleteById(){
        //create
        String [] type= {"java,Spring"};
        Article article = new Article();
        article.setType("Technical");
        article.setName("kousik");
        article.setTags(type);
        article.setAuthorId(123);
        article.setLikes(10);
        article.setVisits(10);
        article.setDuration("20 min");

        Article articleCreated = articleService.createOrUpdateArticle(article);
        //delete
        articleService.deleteById(articleCreated.getId());
        //get article by ID that will raise ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class,()->articleService.getArticleById(articleCreated.getId()));

    }

    @Test
    @DisplayName("testing getAllArticles")
    void testGetAllArticles(){
        //article 1
        String [] type= {"java,Spring"};
        Article article1 = new Article();
        article1.setType("Technical");
        article1.setName("kousik");
        article1.setTags(type);
        article1.setAuthorId(123);
        article1.setLikes(10);
        article1.setVisits(10);
        article1.setDuration("20 min");

        //article 2
        Article article2 = new Article();
        article2.setType("Technical");
        article2.setName("Aditya");
        article2.setTags(type);
        article2.setAuthorId(123);
        article2.setLikes(10);
        article2.setVisits(10);
        article2.setDuration("20 min");

        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);

        //create Articles using createOrUpdateArticles
        Article articleCreated1 = articleService.createOrUpdateArticle(article1);
        Article articleCreated2 = articleService.createOrUpdateArticle(article2);

        List<Article> finalGetArticles = new ArrayList<>();
        Map<String,String> qParams= new HashMap<>();
        finalGetArticles=articleService.getAllArticles(qParams);

        assertEquals(article1.getName(), finalGetArticles.get(0).getName());
        assertEquals(article1.getVisits(),finalGetArticles.get(0).getVisits());


    }
}