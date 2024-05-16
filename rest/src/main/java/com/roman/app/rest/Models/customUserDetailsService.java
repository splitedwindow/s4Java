package com.roman.app.rest.Models;

import com.roman.app.rest.Repo.rolesRepo;
import com.roman.app.rest.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class customUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepo userR;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<user> userOpt = userR.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        user currentUser = userOpt.get();

        return new User(currentUser.getEmail(), currentUser.getPassword(), mapRolesToAuthorities(userR.findAll()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<user> Users) {
        return Users.stream().map(User -> new SimpleGrantedAuthority(User.getRole())).collect(Collectors.toList());
    }
}

