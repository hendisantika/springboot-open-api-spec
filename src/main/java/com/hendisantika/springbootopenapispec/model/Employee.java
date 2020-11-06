package com.hendisantika.springbootopenapispec.model;

import com.squareup.moshi.Json;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-open-api-spec
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/11/20
 * Time: 08.38
 */
@Data
public class Employee extends EmployeeRequest {

    @Json(name = "id")
    private String id;

    public Employee() {
        super();
        id = null;
    }

}
