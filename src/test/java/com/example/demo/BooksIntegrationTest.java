package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.example.demo.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BooksIntegrationTest extends DemoApplicationTests {
    @Test
    void shouldReturnCreateAndDeleteBook() throws Exception {
        String endpoint = "/api/books";

        Book newBook = new Book() {
            {
                setAuthorName("TEST");
                setBookName("TEST");
                setIsbn("TEST");
            }
        };

        ObjectMapper mapper = new ObjectMapper();

        var response = this.mockMvc.perform(
                post(endpoint)
                        .content(mapper.writeValueAsBytes(newBook))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.book-name").value(newBook.getBookName()))
                .andExpect(jsonPath("$.author-name").value(newBook.getAuthorName()))
                .andExpect(jsonPath("$.isbn").value(newBook.getIsbn()))
                .andReturn().getResponse().getContentAsString();

        newBook = mapper.readValue(response, Book.class);

        shouldReturnBookById(newBook.getId());

        shouldUpdateBook(newBook.getId());

        shouldRemoveBook(newBook.getId());
    }

    @Test
    void shouldReturnListOfEmployee() throws Exception {
        String endpoint = "/api/books";

        this.mockMvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    void shouldReturnBookById(String id) throws Exception {
        String endpoint = "/api/books/{id}";
        // String id = "a657c84b-0df5-4026-ab4a-7eebaf1c19a9";

        this.mockMvc.perform(get(endpoint, id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.book-name").exists())
                .andExpect(jsonPath("$.author-name").exists())
                .andExpect(jsonPath("$.isbn").exists());
    }

    void shouldUpdateBook(String id) throws Exception {
        String endpoint = "/api/books/{id}";
        // String id = "a657c84b-0df5-4026-ab4a-7eebaf1c19a9";

        Book newEmployee = new Book() {
            {
                setAuthorName("test");
                setBookName("test");
                setIsbn("test");
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(
                put(endpoint, id)
                        .content(mapper.writeValueAsBytes(newEmployee))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.author-name").value("test"))
                .andExpect(jsonPath("$.book-name").value("test"))
                .andExpect(jsonPath("$.isbn").value("test"));
    }

    void shouldRemoveBook(String id) throws Exception {
        String endpoint = "/api/books/{id}";
        this.mockMvc.perform(
                delete(endpoint, id))
                .andExpect(status().isOk());
    }

}
