package com.excentro.geekbrains.hw6.controllers;

import java.util.List;
import com.excentro.geekbrains.lesson6.entity.Employee;
import com.excentro.geekbrains.lesson6.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EpmloyeeController {
  private final EmployeeRepository repository;

  public EpmloyeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/employee")
  public List<Employee> all() {
    return repository.findAll();
  }

  @PostMapping("/employee")
  public Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }
}
