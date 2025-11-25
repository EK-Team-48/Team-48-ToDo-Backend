package com.example.kromannreumert;

import com.example.kromannreumert.logging.repository.LogRepository;
import com.example.kromannreumert.user.entity.Role;
import com.example.kromannreumert.user.entity.User;
import com.example.kromannreumert.user.repository.RoleRepository;
import com.example.kromannreumert.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class KromannReumertApplication {

    public static void main(String[] args) {
        SpringApplication.run(KromannReumertApplication.class, args);
    }
    
    @Bean
    CommandLineRunner loadTestData(UserRepository userRepo, RoleRepository roleRepository) {
        return args -> {

            Date now = new Date(2025-11-25L);
            Role admin = new Role(null,"ADMIN");
            Role user  = new Role(null, "USER");
            roleRepository.save(admin);
            roleRepository.save(user);


            User u = new User(null,"testuser","test","test@test.dk","test", now,Set.of(admin,user));
            u.setUsername("testuser");
            u.setPassword("password");
            u.setRoles(Set.of(admin, user));

            userRepo.save(u);

            System.out.println("Test data indlæst i databasen");
        };
    }

}
