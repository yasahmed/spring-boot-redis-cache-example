package com.sg.demo.repository;

import com.sg.demo.domain.User;

import java.util.Map;


public interface UserRepository {

    User save(User user);

    User find(Object id);

    Map<Object, User> findAll();

    void update(User user);

    void delete(Object id);
}
