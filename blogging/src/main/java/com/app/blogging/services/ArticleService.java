package com.app.blogging.services;

import com.app.blogging.exceptions.ResourceNotFoundException;
import com.app.blogging.model.Article;
import com.app.blogging.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticles(Map<String,String> qparams) {
       List<Article> list = new ArrayList<>();
        list=articleRepository.findAll();
       for(Map.Entry m:qparams.entrySet()){
           if(m.getKey().equals("name")){
               list=articleRepository.findByName(qparams.get(m.getKey()));
               break;
           }
           else if (m.getKey().equals("id")) {
               Article article=articleRepository.findById(Long.valueOf(qparams.get(m.getKey()))).orElseThrow(()-> {
                   return new ResourceNotFoundException("Article is not found with" +
                           " id: " + qparams.get(m.getKey()));
               });
               list.clear();
               list.add(article);
               break;
           }else {
               break;
           }
       }
       return list;
    }

    public Article getArticleById(long id){
        Article article = articleRepository.findById(id).orElseThrow(()-> {
            return new ResourceNotFoundException("Article is not found with" +
                    " id: " + id);
        });;
        return article;
    }

    public Article createOrUpdateArticle(Article article){
        Article articleCreatedOrUpdate = articleRepository.save(article);
        return articleCreatedOrUpdate;
    }

    public void deleteById(long id){

        Article article = articleRepository.findById(id).orElseThrow(()-> {
            return new ResourceNotFoundException("Article is not found with" +
                    " id: " + id);
        });
        articleRepository.deleteById(id);
    }

    public Article updateById(Article article, long id){
        Article articleDb = articleRepository.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("Article is not found with" +
                    " id: " + id);
        });
        if(Objects.nonNull(article.getName())
            && !"".equalsIgnoreCase(article.getName())){
            articleDb.setName(article.getName());
        }
        if(Objects.nonNull(article.getDuration())
                && !"".equalsIgnoreCase(article.getDuration())){
            articleDb.setDuration(article.getDuration());
        }
        if(Objects.nonNull(article.getVisits())){
            articleDb.setVisits(article.getVisits());
        }
        if(Objects.nonNull(article.getLikes())){
            articleDb.setLikes(article.getLikes());
        }
        if(Objects.nonNull(article.getTags())){
            articleDb.setTags(article.getTags());
        }
        if(Objects.nonNull(article.getType())
                && !"".equalsIgnoreCase(article.getType())){
            articleDb.setType(article.getType());
        }

        Article article1= articleRepository.save(articleDb);
        return article1;

    }

}
