package com.app.blogging.controller;


import com.app.blogging.model.Article;
import com.app.blogging.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @GetMapping("/article")
    public ResponseEntity<Object> getAllArticles(@RequestParam(required = false) Map<String,String> qParams){
        List<Article> getAllListOfArticles = articleService.getAllArticles(qParams);
        return new ResponseEntity<>(getAllListOfArticles, HttpStatus.OK);
    }

    @PostMapping("/article")
    public ResponseEntity<Object> createArticle(@Valid @RequestBody Article articles){
        Article article = articleService.createOrUpdateArticle(articles);
        return new ResponseEntity<Object>(article,HttpStatus.CREATED);
    }
    @PutMapping("/article")
    public ResponseEntity<Object> update(@Valid @RequestBody Article articles){
        Article article = articleService.createOrUpdateArticle(articles);
        return new ResponseEntity<Object>(article,HttpStatus.CREATED);
    }

    @GetMapping("/article/{articleid}")
    public ResponseEntity<Object> getArticleById(@PathVariable("articleid") long id){
        Article article= articleService.getArticleById(id);
        return new ResponseEntity<Object>(article,HttpStatus.OK);
    }
    @PatchMapping("/article/{articleid}")
    public ResponseEntity<Object> updateById(@Valid @RequestBody Article articles,
                                                 @PathVariable("articleid") long id){
        Article article= articleService.updateById(articles,id);
        return new ResponseEntity<Object>(article,HttpStatus.OK);
    }
    @DeleteMapping("/article/{articleid}")
    public ResponseEntity<Object> deleteById(@PathVariable("articleid") long id){
        articleService.deleteById(id);
        return new ResponseEntity<Object>("Deleted Successfully",HttpStatus.NO_CONTENT);
    }


}
