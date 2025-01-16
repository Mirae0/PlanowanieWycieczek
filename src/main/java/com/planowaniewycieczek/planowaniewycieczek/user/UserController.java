package com.planowaniewycieczek.planowaniewycieczek.user;

import org.springframework.beans.factory.annotation.Autowired;
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
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return userService.saveUser(userEntity);
    }

    @PostMapping("/searchUserByEmail")
    public List<UserEntity> searchUserByEmail(@RequestParam("email") String email) {
        List<UserEntity> userEntities = userService.findUsersByEmail(email);
        return userEntities;
    }


}
