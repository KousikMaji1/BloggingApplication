package com.app.blogging.services;

import com.app.blogging.exceptions.ResourceNotFoundException;
import com.app.blogging.model.Author;
import com.app.blogging.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAllAuthor(Map<String,String> qparams) {
        List<Author> list = new ArrayList<>();
        list = authorRepository.findAll();
        for (Map.Entry m : qparams.entrySet()) {
            if (m.getKey().equals("name")) {
                list = authorRepository.findByName(qparams.get(m.getKey()));
                break;
            } else if (m.getKey().equals("email")) {
                list = authorRepository.findByEmail(qparams.get(m.getKey()));
                break;
            } else if (m.getKey().equals("id")) {
                Author author = authorRepository.findById(Long.valueOf(qparams.get(m.getKey()))).orElseThrow(()-> {
                    return new ResourceNotFoundException("Author is not found with" +
                            " id: " + qparams.get(m.getKey()));
                });;
                list.add(author);
                break;
            }else {

                break;
            }
        }
        return list;
    }
    public Author getAuthorById(long id){
        Author author;
        author = authorRepository.findById(id).orElseThrow(()-> {
            return new ResourceNotFoundException("Author is not found with" +
                    " id: " + id);
        });
        return author;
    }
    public Author createOrUpdateAuthor(Author author){
        Author authorCreatedOrUpdate = authorRepository.save(author);
        return authorCreatedOrUpdate;
    }

    public void deleteById(long id){
        Author author = authorRepository.findById(id).orElseThrow(()-> {
            return new ResourceNotFoundException("Author is not found with" +
                    " id: " + id);
        });
        authorRepository.deleteById(id);
    }

    public Author updateById(Author author, long id){
        Author authorDb = authorRepository.findById(id).orElseThrow(()-> {
            return new ResourceNotFoundException("Author is not found with" +
                    " id: " + id);
        });
        if(Objects.nonNull(author.getName())
                && !"".equalsIgnoreCase(author.getName())){
            authorDb.setName(author.getName());
        }
        if(Objects.nonNull(author.getEmail())
                && !"".equalsIgnoreCase(author.getEmail())){
            authorDb.setEmail(author.getEmail());
        }
        if(Objects.nonNull(author.getPassword())
                && !"".equalsIgnoreCase(author.getPassword())){
            authorDb.setPassword(author.getPassword());
        }

        Author author1= authorRepository.save(authorDb);
        return author1;

    }
}
