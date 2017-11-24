package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService service;

    @RequestMapping("/all")
    public List<User> getUser(){
        List<User> list = service.getAll();
        logger.info(list.toString());
        return list;
    }
}
