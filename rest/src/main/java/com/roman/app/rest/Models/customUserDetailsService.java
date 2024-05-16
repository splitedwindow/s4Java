package com.roman.app.rest.Models;

import com.roman.app.rest.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailsService implements UserDetailsService {
    @Autowired
    userRepo customerR;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        user User = customerR.findByEmail(email);

        if(email == null) {
            throw new UsernameNotFoundException("Could not find user with email");
        }
        return new customUserDetails(User);
    }
}
