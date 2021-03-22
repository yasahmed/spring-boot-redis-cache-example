package com.sg.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.demo.domain.User;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;

public class JsonUtilsExample {
    public static User createUserFromJson() throws IOException {
        return new ObjectMapper().readValue(ResourceUtils.getFile("classpath:jsons/user.json"), User.class);
    }

    public static List<User> createUsersFromJson() throws IOException {
        return new ObjectMapper().readValue(ResourceUtils.getFile("classpath:jsons/users.json"), new TypeReference<List<User>>() {
        });
    }
}
