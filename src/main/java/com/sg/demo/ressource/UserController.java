package com.sg.demo.ressource;

import com.sg.demo.domain.User;
import com.sg.demo.service.UserService;
import com.sg.demo.utils.JsonUtilsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public void setUserData(@RequestParam String id, @RequestParam String name, @RequestParam int score) {
        userService.setUserScore(id, name, score);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping("/expire/{id}")
    public void expireUser(@PathVariable long id) {
        userService.expire(id);
    }

    @GetMapping("/user")
    public User getUser() throws IOException {
        return JsonUtilsExample.createUserFromJson();
    }

    @GetMapping("/users")
    public List<User> getUsers() throws IOException {
        return JsonUtilsExample.createUsersFromJson();
    }
}
