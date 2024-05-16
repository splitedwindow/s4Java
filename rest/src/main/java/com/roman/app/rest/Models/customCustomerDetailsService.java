package com.roman.app.rest.Models;

import com.roman.app.rest.Repo.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customCustomerDetailsService implements UserDetailsService {
    @Autowired
    customerRepo customerR;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        customer Customer = customerR.findByEmail(email);

        if(email == null) {
            throw new UsernameNotFoundException("Could not find user with email");
        }
        return new customCustomerDetails(Customer);
    }
}
