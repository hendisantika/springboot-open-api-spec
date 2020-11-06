package com.hendisantika.springbootopenapispec.model;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-open-api-spec
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/11/20
 * Time: 08.39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @Json(name = "first_name")
    private String firstName;

    @Json(name = "last_name")
    private String lastName;
}
