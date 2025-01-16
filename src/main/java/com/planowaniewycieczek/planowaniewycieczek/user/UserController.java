package com.planowaniewycieczek.planowaniewycieczek.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

<<<<<<< Updated upstream
=======
    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @PostMapping("/searchUserByEmail")
    public List<UserEntity> searchUserByEmail(@RequestParam("email") String email) {
        List<UserEntity> users = userService.findUsersByEmail(email);
        return users;
    }

>>>>>>> Stashed changes

}
