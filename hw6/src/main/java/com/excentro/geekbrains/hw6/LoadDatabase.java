package com.excentro.geekbrains.hw6;

import com.excentro.geekbrains.lesson6.entity.Employee;
import com.excentro.geekbrains.lesson6.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
  private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDB(EmployeeRepository repository) {
    return args -> {
      LOG.info("Preloading {}", repository.save(new Employee("Mike", "worker")));
      LOG.info("Preloading {}", repository.save(new Employee("Pete", "boss")));
    };
  }
}
