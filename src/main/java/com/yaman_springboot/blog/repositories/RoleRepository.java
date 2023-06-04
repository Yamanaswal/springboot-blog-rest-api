package com.yaman_springboot.blog.repositories;

import com.yaman_springboot.blog.models.jpa_models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);


}
