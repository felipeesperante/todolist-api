package br.com.nunesonline.todolistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.nunesonline")
@EnableJpaRepositories(basePackages = "br.com.nunesonline")
public class ToDoListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApiApplication.class, args);
	}

}
