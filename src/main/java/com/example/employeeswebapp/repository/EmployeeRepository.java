package com.example.employeeswebapp.repository;

import com.example.employeeswebapp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT p FROM Employee p WHERE CONCAT(p.name, ' ', p.brand, ' ', p.description) LIKE %?1%")
    Page<Employee> findAll(String keyword, Pageable pageable);
}
