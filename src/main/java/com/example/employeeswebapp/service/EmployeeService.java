package com.example.employeeswebapp.service;

import com.example.employeeswebapp.model.Product;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    void saveEmployee(Product employee);
    Product getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Product> listAll(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}
