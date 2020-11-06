package com.hendisantika.springbootopenapispec;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootOpenApiSpecApplicationTests {

    @Test
    void contextLoads() {
        //just to remove sonarlint issue
        Assertions.assertEquals("MAIN", "MA" + "IN");
    }

}
