package com.hendisantika.springbootopenapispec.controller;

import com.hendisantika.springbootopenapispec.model.Employee;
import com.hendisantika.springbootopenapispec.model.EmployeeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-open-api-spec
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/11/20
 * Time: 08.54
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {
    @Value("${local.server.port}")
    private int randomServerPort; //random server port assigned by spingboot test

    @Value("${server.servlet.context-path}")
    private String contextRoot;

    private String baseUrl;

    private HttpHeaders httpHeaders;

    @BeforeEach
    void setUp() {

        System.out.println("API PORT :" + randomServerPort);
        baseUrl = "http://127.0.0.1:" + randomServerPort + contextRoot + "/employees";
        System.out.println("BASE URL :" + baseUrl);

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("Headers are set");
    }

    @Test
    void addNewEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Uzumaki");
        request.setLastName("Naruto");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity,
                Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(Boolean.FALSE, Objects.requireNonNull(responseEntity.getBody()).getId().isEmpty());
    }

    @Test
    void getAllEmployees() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Uzumaki");
        request.setLastName("Naruto");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity,
                Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        ResponseEntity<Object[]> entity = new TestRestTemplate().exchange(baseUrl, HttpMethod.GET,
                new HttpEntity<>(httpHeaders), Object[].class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(Boolean.TRUE, entity.getBody().length >= 1);
    }

    @Test
    void getEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Uzumaki");
        request.setLastName("Naruto");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity,
                Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        String url = baseUrl + "/" + responseEntity.getBody().getId();
        System.out.println(url);

        ResponseEntity<Employee> entity = new TestRestTemplate().exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaders), Employee.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(request.getFirstName(), entity.getBody().getFirstName());
        Assertions.assertEquals(request.getLastName(), entity.getBody().getLastName());
    }

    @Test
    void updateExistingEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Uzumaki");
        request.setLastName("Naruto");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity,
                Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Employee originalEmployee = responseEntity.getBody();

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(originalEmployee.getId());
        request.setFirstName("Uchiha");
        request.setLastName("Sasuke");

        httpEntity = new HttpEntity<>(updatedEmployee, httpHeaders);
        ResponseEntity<Employee> entity = new TestRestTemplate().exchange(baseUrl, HttpMethod.PUT, httpEntity,
                Employee.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(originalEmployee.getId(), entity.getBody().getId());
        Assertions.assertNotEquals(originalEmployee.getFirstName(), entity.getBody().getFirstName());
        Assertions.assertNotEquals(originalEmployee.getLastName(), entity.getBody().getLastName());
    }

}