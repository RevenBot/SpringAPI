package com.example.demo.repositories;
import com.example.demo.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
