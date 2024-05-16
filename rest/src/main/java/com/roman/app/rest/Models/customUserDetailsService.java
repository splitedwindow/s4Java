package com.roman.app.rest.Models;

import com.roman.app.rest.Repo.rolesRepo;
import com.roman.app.rest.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class customUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepo customerR;

    @Autowired
    private rolesRepo rolesR;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        user User = customerR.findByEmail(email);

        if (User == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Optional<roles> roleOpt = rolesR.findByRoleId(User.getRoleId());
        roles role = roleOpt.orElseThrow(() -> new UsernameNotFoundException("Role not found for user with email: " + email));

        return new customUserDetails(User, Collections.singletonList(role));
    }
}

