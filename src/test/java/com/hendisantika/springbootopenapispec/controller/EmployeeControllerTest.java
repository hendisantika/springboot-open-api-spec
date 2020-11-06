package com.hendisantika.springbootopenapispec.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

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

}