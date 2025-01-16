package com.planowaniewycieczek.planowaniewycieczek.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getUsers(){
        return List.of(
                new User("Jan")
        );
    }
<<<<<<< Updated upstream
}
=======

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


}
>>>>>>> Stashed changes
