package com.example.demo.persistance.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public enum Role {

    ADMIN,

    CUSTOMER;

    private static final String ROLE_PREFIX = "ROLE_";

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + this.name()));

        return authorities;
        }
}
