package com.yaman_springboot.blog;

import com.yaman_springboot.blog.models.jpa_models.Role;
import com.yaman_springboot.blog.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;

@SpringBootApplication
public class SpringBootBlogRestApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBlogRestApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        //Add Admin Role..
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        if (!roleRepository.existsByName(adminRole.getName())) {
            roleRepository.save(adminRole);
        }

        //Add User Role..
        Role userRole = new Role();
        userRole.setName("USER");
        if (!roleRepository.existsByName(userRole.getName())) {
            roleRepository.save(userRole);
        }

    }
}
