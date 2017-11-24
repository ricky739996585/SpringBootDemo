package com.example.service.impl;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getAll() {
        return mapper.list();
    }

    @Override
    public User getUser(Integer id) {
        return mapper.get(id);
    }

    @Override
    public int insert(User user) {
        return mapper.add(user);
    }

    @Override
    public void update(User user) {
        mapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        mapper.delete(id);
    }
}
