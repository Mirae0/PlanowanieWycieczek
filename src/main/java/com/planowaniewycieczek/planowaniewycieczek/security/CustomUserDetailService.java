package com.planowaniewycieczek.planowaniewycieczek.security;

import com.planowaniewycieczek.planowaniewycieczek.user.UserEntity;
import com.planowaniewycieczek.planowaniewycieczek.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserEntity>user = userRepository.findByUsername(username);
    if(user.isPresent()){
        var userObj = user.get();
        return User.builder().username(userObj.getUsername()).password(userObj.getPassword()).roles("USER").build();
    }else{
       throw new UsernameNotFoundException(username);
    }

    }
}
