package com.planowaniewycieczek.planowaniewycieczek.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity findByUsername(String username) {return userRepository.findByUsername(username).orElse(null);}


    public List<UserEntity> findUsersByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public List<UserEntity> findUsersByUsername(String username) {
        return userRepository.findAllByUsernameContaining(username);
    }


}