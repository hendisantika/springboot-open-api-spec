package com.hendisantika.springbootopenapispec.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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
}