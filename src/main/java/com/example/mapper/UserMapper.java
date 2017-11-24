package com.example.mapper;

import com.example.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
@Repository
public interface UserMapper {
    public List<User> list();

    public User get(Integer id);

    public int add(User user);

    public void update(User user);

    public void delete(Integer id);
}
