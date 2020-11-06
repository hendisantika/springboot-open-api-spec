package com.hendisantika.springbootopenapispec.service;

import com.hendisantika.springbootopenapispec.model.Employee;
import com.hendisantika.springbootopenapispec.model.EmployeeRequest;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-open-api-spec
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/11/20
 * Time: 08.41
 */
public interface EmployeeService {
    /**
     * Method will add a new employee and generate a unique id for employee
     *
     * @param request
     * @return
     */
    Employee addNewEmployee(EmployeeRequest request);
}
