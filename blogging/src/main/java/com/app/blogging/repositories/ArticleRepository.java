package com.app.blogging.repositories;

import com.app.blogging.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository <Article,Long> {
    public List<Article> findByName(String name);
}
