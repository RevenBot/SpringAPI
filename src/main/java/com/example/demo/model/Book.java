package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JsonProperty("bookName")
    @Column(name = "book_name", nullable = false)
    private String bookName;
    @JsonProperty("authorName")
    @Column(name = "author_name")
    private String authorName;
    private String isbn;

}
