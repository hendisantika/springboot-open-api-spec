package com.hendisantika.springbootopenapispec.controller;

import com.hendisantika.springbootopenapispec.model.Employee;
import com.hendisantika.springbootopenapispec.model.EmployeeRequest;
import com.hendisantika.springbootopenapispec.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-open-api-spec
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/11/20
 * Time: 08.52
 */
@RestController
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Endpoint to add new employee
     *
     * @param employeeRequest
     * @return
     */
    @PostMapping("/employees")
    @Operation(summary = "Add New Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New Employee added", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema =
            @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema =
            @Schema(hidden = true))})
    })
    public ResponseEntity<Employee> addNewEmployee(@RequestBody EmployeeRequest employeeRequest) {
        logger.info("TIMESTAMP:{}:Add New Employee:First Name - {}:Last Name - {}", System.currentTimeMillis(),
                employeeRequest.getFirstName(), employeeRequest.getLastName());
        Employee employee = employeeService.addNewEmployee(employeeRequest);
        logger.info("TIMESTAMP:{}:Add New Employee:Id - {}:First Name - {}:Last Name - {}",
                System.currentTimeMillis(), employee.getId(), employee.getFirstName(), employee.getLastName());
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    /**
     * Endpoint to get all employees
     *
     * @return
     */
    @GetMapping("/employees")
    @Operation(summary = "Get all Employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Employees returned", content = {@Content(mediaType
                    = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema =
            @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema =
            @Schema(hidden = true))})
    })
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getEmployees();
        logger.info("TIMESTAMP:{}:Get all Employee:Count - {}", System.currentTimeMillis(), employeeList.size());
        return ResponseEntity.ok(employeeList);
    }

    /**
     * Endpoint to get employee by id
     *
     * @return
     */
    @GetMapping("/employees/{employee-id}")
    @Operation(summary = "Get Employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee returned", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema =
            @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema =
            @Schema(hidden = true))})
    })
    public ResponseEntity<Employee> getEmployee(@Parameter(description = "Id to get Employee") @PathVariable(
            "employee-id") String employeeId) {
        logger.info("TIMESTAMP:{}:Get Employee:id - {}", System.currentTimeMillis(), employeeId);
        Employee employee = employeeService.getEmployee(employeeId);
        if (!employee.getId().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employee);
    }

    /**
     * Endpoint to update employee
     *
     * @return
     */
    @PutMapping("/employees")
    @Operation(summary = "Update Employee else add new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "201", description = "Employee created", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema =
            @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema =
            @Schema(hidden = true))})
    })
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        logger.info("TIMESTAMP:{}:Update Employee:id - {}", System.currentTimeMillis(), employee.getId());
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        if (updatedEmployee.getId().equalsIgnoreCase(employee.getId()))
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedEmployee);
    }

    /**
     * Endpoint to get delete by id
     *
     * @return
     */
    @DeleteMapping("/employees/{employee-id}")
    @Operation(summary = "Delete Employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema =
            @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema =
            @Schema(hidden = true))})
    })
    public ResponseEntity<Employee> deleteEmployee(@Parameter(description = "Id to delete Employee") @PathVariable(
            "employee-id") String employeeId) {
        logger.info("TIMESTAMP:{}:Delete Employee:id - {}", System.currentTimeMillis(), employeeId);
        Employee employee = employeeService.removeEmployee(employeeId);
        if (!employee.getId().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employee);
    }

}
