package com.teluguskillhub.springsecurity.security;

import com.teluguskillhub.springsecurity.Entities.User;
import com.teluguskillhub.springsecurity.Exception.UserNotFoundException;
import com.teluguskillhub.springsecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        ()->new UserNotFoundException(String.format("User with email:%s is not found",email)
                        ));

        Set<String> roles=new HashSet<>();
        roles.add("ROLE_ADMIN");
        Collection<? extends GrantedAuthority> authorities = userAuthorities(roles);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
    private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
        return roles
                .stream()
                .map(
                        role->new SimpleGrantedAuthority(role)
                )
                .toList();
    }
}
