package com.tanuja.employee_management.service;

import com.tanuja.employee_management.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(long id);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(long id, EmployeeDto updatedEmployeeDto);
    void deleteEmployee(long id);
}
