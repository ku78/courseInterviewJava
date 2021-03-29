package com.excentro.geekbrains.hw7.controller;

import com.excentro.geekbrains.lesson7.entity.Student;
import com.excentro.geekbrains.lesson7.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {
  private final StudentRepository repository;

  public StudentController(StudentRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public String allStudents(Model model) {
    model.addAttribute("students", repository.findAll());
    return "students";
  }

  @GetMapping("/new")
  public String addStudent(Model model) {
    Student student = new Student("", 0);
    model.addAttribute("student", student);
    return "student";
  }

  @GetMapping("/{id}")
  public String editStudent(@PathVariable("id") Long id, Model model) {
    Student student = repository.findById(id).orElse(new Student());
    model.addAttribute("student", student);
    return "student";
  }

  @PostMapping("/update")
  public String updateStudent(Student student) {
    repository.save(student);
    return "redirect:/students";
  }

  @DeleteMapping("/{id}/delete")
  public String deleteStudent(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return "redirect:/students";
  }

  @PostConstruct
  public void init() {
    Student student1 = new Student("student1", 20);
    Student student2 = new Student("student2", 21);
    Student student3 = new Student("student3", 22);

    List<Student> students = Arrays.asList(student1, student2, student3);
    repository.saveAll(students);
  }
}
