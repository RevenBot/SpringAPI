package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeIntegrationTest extends DemoApplicationTests {
    @Test
    void shouldReturnCreateAndDeleteEmployee() throws Exception {
        String endpoint = "/api/employees";

        Employee newEmployee = new Employee() {
            {
                setFirstName("Jim");
                setLastName("Carter");
                setEmail("j.carter@gmail.com");
            }
        };

        ObjectMapper mapper = new ObjectMapper();

        var response = this.mockMvc.perform(
                post(endpoint)
                        .content(mapper.writeValueAsBytes(newEmployee))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.first-name").value(newEmployee.getFirstName()))
                .andExpect(jsonPath("$.last-name").value(newEmployee.getLastName()))
                .andExpect(jsonPath("$.email").value(newEmployee.getEmail()))
                .andReturn().getResponse().getContentAsString();

        newEmployee = mapper.readValue(response, Employee.class);

        shouldReturnEmployeeById(newEmployee.getId());

        shouldUpdateEmployee(newEmployee.getId());

        shouldRemoveEmployee(newEmployee.getId());
    }

    @Test
    void shouldReturnListOfEmployee() throws Exception {
        String endpoint = "/api/employees";

        this.mockMvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    void shouldReturnEmployeeById(String id) throws Exception {
        String endpoint = "/api/employees/{id}";
        // String id = "a657c84b-0df5-4026-ab4a-7eebaf1c19a9";

        this.mockMvc.perform(get(endpoint, id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.first-name").exists())
                .andExpect(jsonPath("$.last-name").exists())
                .andExpect(jsonPath("$.email").exists());
    }

    // @Test
    void shouldUpdateEmployee(String id) throws Exception {
        String endpoint = "/api/employees/{id}";
        // String id = "a657c84b-0df5-4026-ab4a-7eebaf1c19a9";

        Employee newEmployee = new Employee() {
            {
                setFirstName("test");
                setLastName("test");
                setEmail("test.test@gmail.com");
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
                .andExpect(jsonPath("$.first-name").value("test"))
                .andExpect(jsonPath("$.last-name").value("test"))
                .andExpect(jsonPath("$.email").value("test.test@gmail.com"));
    }

    void shouldRemoveEmployee(String id) throws Exception {
        String endpoint = "/api/employees/{id}";
        this.mockMvc.perform(
                delete(endpoint, id))
                .andExpect(status().isOk());
    }
}
