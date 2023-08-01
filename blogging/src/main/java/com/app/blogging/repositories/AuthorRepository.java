package com.app.blogging.repositories;

import com.app.blogging.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository <Author,Long> {
    public List<Author> findByName(String name);
    public List<Author> findByEmail(String email);
}
