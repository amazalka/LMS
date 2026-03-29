package com.example.lms.testController;

import com.example.lms.dto.request.TeacherRequest;
import com.example.lms.dto.response.TeacherResponse;
import com.example.lms.model.TeacherEntity;
import com.example.lms.repository.TeacherRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TeacherControllerTest {
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    void set() {
        TeacherEntity teacher = new TeacherEntity();
        teacher.setName("Sergei");
        teacher.setLastName("Ustyugov");
        teacherRepository.save(teacher);
    }

    @AfterEach
    void delete() {
        teacherRepository.deleteAll();
    }

    @Test
    void shouldCreateTeacher() {
        TeacherRequest request = new TeacherRequest();
        request.setName("Josh");
        request.setLastName("Hutcherson");
        ResponseEntity<TeacherResponse> response = restTemplate.postForEntity("/api/v1/teachers", request, TeacherResponse.class);
        assertEquals("Josh", response.getBody().getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldDeleteTeacher() {
        TeacherRequest request = new TeacherRequest();
        request.setName("Josh");
        request.setLastName("Hutcherson");
        ResponseEntity<TeacherResponse> response = restTemplate.postForEntity("/api/v1/teachers", request, TeacherResponse.class);
        Long id = response.getBody().getId();
        restTemplate.delete("/api/v1/teachers/{id}", id);
        ResponseEntity<TeacherResponse> deletedResponse = restTemplate.getForEntity("/api/v1/teachers/{id}", TeacherResponse.class, id);
        assertEquals(HttpStatus.NOT_FOUND, deletedResponse.getStatusCode());
    }

    @Test
    void shouldUpdateTeacher() {
        TeacherRequest request = new TeacherRequest();
        request.setName("Josh");
        request.setLastName("Hutcherson");
        ResponseEntity<TeacherResponse> response = restTemplate.postForEntity("/api/v1/teachers", request, TeacherResponse.class);
        Long id = response.getBody().getId();
        TeacherRequest newRequest = new TeacherRequest();
        newRequest.setName("Steve");
        newRequest.setLastName("Harrington");
        restTemplate.put("/api/v1/teachers/{id}", newRequest, id);
        ResponseEntity<TeacherResponse> updateResponse = restTemplate.getForEntity("/api/v1/teachers/{id}", TeacherResponse.class, id);
        assertEquals("Steve", updateResponse.getBody().getName());
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
    }

    @Test
    void shouldGetAllTeachers() {
        ResponseEntity<TeacherResponse[]> response = restTemplate.getForEntity("/api/v1/teachers?page=0&size=10", TeacherResponse[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void shouldGetByIdTeacher() {
        TeacherRequest request = new TeacherRequest();
        request.setName("Josh");
        request.setLastName("Hutcherson");
        ResponseEntity<TeacherResponse> response = restTemplate.postForEntity("/api/v1/teachers", request, TeacherResponse.class);
        Long id = response.getBody().getId();
        ResponseEntity<TeacherResponse> getResponse = restTemplate.getForEntity("/api/v1/teachers/{id}", TeacherResponse.class, id);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Josh", getResponse.getBody().getName());
    }
}
