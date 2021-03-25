package com.excentro.geekbrains.hw6.repository;

import com.excentro.geekbrains.lesson6.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
