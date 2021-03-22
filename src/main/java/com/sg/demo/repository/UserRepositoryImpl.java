package com.sg.demo.repository;

import com.sg.demo.domain.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String KEY = "USER";

    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Object, User> hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void expire(User user) {
        this.redisTemplate.expire(KEY.concat(user.getId()), 1, TimeUnit.MINUTES);
        update(user);
    }

    @Override
    public User save(User user) {
        this.hashOperations.put(KEY.concat(user.getId()), user.getId(), user);
        return user;
    }

    @Override
    public User find(Object id) {
        return this.hashOperations.get(KEY.concat(id.toString()), id.toString());
    }

    @Override
    public Map<Object, User> findAll() {
        return this.hashOperations.entries(KEY);
    }

    @Override
    public void update(User user) {
        this.hashOperations.put(KEY.concat(user.getId()), user.getId(), user);
    }

    @Override
    public void delete(Object id) {
        this.hashOperations.delete(KEY.concat(id.toString()), id.toString());
    }
}
