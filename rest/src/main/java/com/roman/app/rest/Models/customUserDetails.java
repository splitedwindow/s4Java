package com.roman.app.rest.Models;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mysql.cj.conf.PropertyKey.logger;

public class customUserDetails implements UserDetails {

    private static final Logger logger = LoggerFactory.getLogger(customUserDetails.class);
    private user User;

    public customUserDetails(user User) {
        super();
        this.User = User;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(User.getRole());
        logger.info("User authorities: {}", authority);
        return Set.of(authority);
    }

    @Override
    public String getPassword() {
        return User.getPassword();
    }

    @Override
    public String getUsername() {
        return User.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
