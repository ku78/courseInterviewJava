package com.excentro.geekbrains.hw7.repo;

import com.excentro.geekbrains.hw7.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {}
