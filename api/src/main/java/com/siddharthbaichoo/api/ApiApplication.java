package com.siddharthbaichoo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.siddharthbaichoo.common.domain")     // <-- finds Payment @Entity
@EnableJpaRepositories(basePackages = "com.siddharthbaichoo.api.repo") // <-- finds your repo
public class ApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }
}
