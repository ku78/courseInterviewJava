package com.excentro.geekbrains.hw7.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Student {
  private @Id @GeneratedValue Long id;
  private String name;
  private int age;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
