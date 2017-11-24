package com.example.service;

import com.example.domain.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User getUser(Integer id);
    public int insert(User user);
    public void update(User user);
    public void delete(Integer id);
}
