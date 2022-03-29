package com.example.employeeswebapp.service;

import com.example.employeeswebapp.model.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Employee> listAll(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}
