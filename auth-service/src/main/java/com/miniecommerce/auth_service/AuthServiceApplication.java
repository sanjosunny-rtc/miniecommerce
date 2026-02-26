package com.miniecommerce.auth_service;

import com.miniecommerce.auth_service.entity.Role;
import com.miniecommerce.auth_service.entity.User;
import com.miniecommerce.auth_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServiceApplication {

	@Bean
	CommandLineRunner runner(UserRepository repo, PasswordEncoder encoder) {
		return args -> {

			// Create ADMIN
			if (repo.findByUsername("admin").isEmpty()) {
				repo.save(User.builder()
						.username("admin")
						.password(encoder.encode("admin123"))
						.role(Role.ADMIN)
						.build());
			}

			// Create Vendor 1
			if (repo.findByUsername("vendor1").isEmpty()) {
				repo.save(User.builder()
						.username("vendor1")
						.password(encoder.encode("vendor123"))
						.role(Role.VENDOR)
						.build());
			}

			// Create Vendor 2 (for ownership testing)
			if (repo.findByUsername("vendor2").isEmpty()) {
				repo.save(User.builder()
						.username("vendor2")
						.password(encoder.encode("vendor123"))
						.role(Role.VENDOR)
						.build());
			}

			// Create Customer
			if (repo.findByUsername("customer1").isEmpty()) {
				repo.save(User.builder()
						.username("customer1")
						.password(encoder.encode("password123"))
						.role(Role.CUSTOMER)
						.build());
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}