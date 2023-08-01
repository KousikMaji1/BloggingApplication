package com.app.blogging.services;

import com.app.blogging.exceptions.ResourceNotFoundException;
import com.app.blogging.model.Author;
import com.app.blogging.repositories.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    @DisplayName("test case for Get all authors details")
    void getAllAuthor() {
        Author author1 = new Author(123,"kousik","kousik.maji@abc.com","abc1");
        Author author2 = new Author(124,"Sami","sami.alam@abc.com","abc1");

        List<Author> authorList = new ArrayList<>();
        authorList.add(author1);
        authorList.add(author2);

        Mockito.when(authorRepository.findAll()).thenReturn(authorList);

        Map<String,String> qParams= new HashMap<>();
        assertEquals(authorService.getAllAuthor(qParams),authorList);

    }

    @Test
    @DisplayName("Find Author information By ID")
    void getAuthorById() {
        Author author = new Author(123,"kousik","kousik.maji@abc.com","abc1");

        Mockito.when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        assertEquals(authorService.getAuthorById(author.getId()),author);

    }

    @Test
    @DisplayName("test for create or update author")
    void createOrUpdateAuthor() {
        Author author = new Author(123,"kousik","kousik.maji@abc.com","abc1");

        Mockito.when(authorRepository.save(author)).thenReturn(author);
        assertEquals(authorService.createOrUpdateAuthor(author),author);
    }

    @Test
    @DisplayName("test for delete author by ID")
    void deleteById() {
        Author author = new Author(123,"kousik","kousik.maji@abc.com","abc1");

        long authorId=123L;
        Mockito.when(authorRepository.findById(authorId)).thenReturn(Optional.of(author)).thenReturn(null);
        authorService.deleteById(123L);
        Mockito.verify(authorRepository).deleteById(authorId);

    }

    @Test
    void updateById() {
        Author author = new Author(123L,"kousik","kousik.maji@abc.com","abc1");

        Mockito.when(authorRepository.save(author)).thenReturn(author);
        author.setEmail("sami.alam@abc.com");
        author.setName("sami");


        Mockito.when(authorRepository.findById(123L)).thenReturn(Optional.of(author));
        Author updateAuthor = authorService.updateById(author,123L);

        assertEquals(updateAuthor.getName(),"sami");
        assertEquals(updateAuthor.getEmail(),"sami.alam@abc.com");


    }

    @Test
    void testResourceNotFoundException(){

        Mockito.when(authorRepository.findById(124L)).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,()->authorService.getAuthorById(124));
    }


}