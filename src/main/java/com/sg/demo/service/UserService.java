package com.sg.demo.service;

import com.sg.demo.domain.User;
import com.sg.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepositoryImpl userRepository;

    public void setUserScore(String id, String name, int score) {
        User user = userRepository.find(id);
        if (user != null) {
            user.setScore(user.getScore() + score);
            userRepository.update(user);
        } else {
            user = User.builder().id(id).name(name).score(score).build();
            userRepository.save(user);
        }
    }

    public User getUser(long id) {
        return userRepository.find(id);
    }

    public void expire(long id) {
        User user = userRepository.find(id);
        if (user != null) {
            userRepository.expire(user);
        }
    }
}
