package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepo extends JpaRepository<user, Long> {
//    Optional<customer> findByEmail(String email);
    Optional<user> findByEmailAndPassword(String email, String password);

    public user findByEmail(String email);
}
