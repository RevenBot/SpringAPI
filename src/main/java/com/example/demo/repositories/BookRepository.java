package com.example.demo.repositories;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
