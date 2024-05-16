package com.roman.app.rest.Models;

import com.roman.app.rest.Repo.rolesRepo;
import com.roman.app.rest.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class customUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepo userR;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        user User = userR.findByEmail(email);

        if (User == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + User.getRole());

        return new org.springframework.security.core.userdetails.User(
                User.getEmail(),
                User.getPassword(),
                Set.of(authority)
        );
    }
}

