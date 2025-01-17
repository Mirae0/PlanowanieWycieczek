package com.planowaniewycieczek.planowaniewycieczek.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @PostMapping("/searchUserByEmail")
    public List<UserEntity> searchUserByEmail(@RequestParam("username") String username) {
        return userService.findUsersByUsername(username);
    }


}
