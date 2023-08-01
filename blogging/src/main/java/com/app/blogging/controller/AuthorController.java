package com.app.blogging.controller;

import com.app.blogging.exceptions.BadRequestException;
import com.app.blogging.exceptions.ResourceNotFoundException;
import com.app.blogging.model.Author;
import com.app.blogging.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/author")
    public ResponseEntity<Object> getAllAuthors(@RequestParam(required = false) Map<String,String> qParams){
        try{
            List<Author> getAllListOfAuthors = authorService.getAllAuthor(qParams);
            return new ResponseEntity<>(getAllListOfAuthors, HttpStatus.OK);
        } catch (ResourceNotFoundException rnf){
            return new ResponseEntity<>(rnf, HttpStatus.OK);
        }
    }

    @PostMapping("/author")
    public ResponseEntity<Object> createAuthor(@Valid @RequestBody Author authors){
        Author author = authorService.createOrUpdateAuthor(authors);
        return new ResponseEntity<Object>(author,HttpStatus.CREATED);
    }
    @PutMapping("/author")
    public ResponseEntity<Object> update(@Valid @RequestBody Author authors){
        Author author = authorService.createOrUpdateAuthor(authors);
        return new ResponseEntity<Object>(author,HttpStatus.CREATED);
    }

    @GetMapping("/author/{authorid}")
    public ResponseEntity<Object> getAuthorById(@PathVariable("authorid") long id){
        Author author= authorService.getAuthorById(id);
        return new ResponseEntity<Object>(author,HttpStatus.OK);
    }
    @PatchMapping("/author/{authorid}")
    public ResponseEntity<Object> updateById(@Valid @RequestBody Author authors,
                                             @PathVariable("authorid") long id){
        Author author= authorService.updateById(authors,id);
        return new ResponseEntity<Object>(author,HttpStatus.OK);
    }
    @DeleteMapping("/author/{authorid}")
    public ResponseEntity<Object> deleteById(@PathVariable("authorid") long id){
        System.out.println("=============");
        authorService.deleteById(id);
        return new ResponseEntity<Object>("Deleted Successfully",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/author/test")
    public ResponseEntity<Object> getAuthorByIdTest(@RequestParam("id") Long id){
        try {
            if(id == null){
                throw new BadRequestException("please send id");
            }
            System.out.println("===============");
            Author author= authorService.getAuthorById(id);
            return new ResponseEntity<Object>(author,HttpStatus.OK);
        }catch (BadRequestException bre){
            return new ResponseEntity<Object>(bre,HttpStatus.OK);
        }
    }

}
