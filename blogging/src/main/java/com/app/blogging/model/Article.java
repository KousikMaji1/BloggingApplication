package com.app.blogging.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min = 2, message = "Article name must have at least 2 characters")
    private String name;
    @NotNull(message = "Author id cannot be empty")
    private long authorId;

    @Size(min = 1, message = "Tags must have at least 1 element")
    private String[] tags;
    @NotBlank(message = "Duration cannot be blank")
    private String duration;
    private int visits;
    private int likes;
    @NotBlank(message = "Block Editor cannot be blank")
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Article(long id, String name, long authorId, String[] tags, String duration, int visits, int likes, String type) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.tags = tags;
        this.duration = duration;
        this.visits = visits;
        this.likes = likes;
        this.type = type;
    }

    public Article() {
    }



}
