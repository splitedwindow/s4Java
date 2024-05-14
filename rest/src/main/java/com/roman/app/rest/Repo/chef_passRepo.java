package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.chef_pass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface chef_passRepo extends JpaRepository<chef_pass, Long> {
    Optional<chef_pass> findByPassword(String password);

}