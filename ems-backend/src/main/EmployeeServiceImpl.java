package com.tanuja.employee_management.service.impl;

import com.tanuja.employee_management.dto.EmployeeDto;
import com.tanuja.employee_management.entity.Employee;
import com.tanuja.employee_management.exception.ResourceNotFoundException;
import com.tanuja.employee_management.mapper.EmployeeMapper;
import com.tanuja.employee_management.repository.EmployeeRepository;
import com.tanuja.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee =EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(long id)  {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given Id:"+id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
    @Override
     public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
     }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto updatedEmployeeDto) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given Id:"+id));
        employee.setFirstName(updatedEmployeeDto.getFirstName());
        employee.setLastName(updatedEmployeeDto.getLastName());
        employee.setEmail(updatedEmployeeDto.getEmail());
        return EmployeeMapper.mapToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given Id:"+id));
         employeeRepository.deleteById(id);
    }


}
