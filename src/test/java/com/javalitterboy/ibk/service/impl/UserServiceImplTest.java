package com.javalitterboy.ibk.service.impl;

import com.javalitterboy.ibk.entity.User;
import com.javalitterboy.ibk.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findUserById() {
        User user = userService.findUserById(1);
        assertNotNull(user);
        assertEquals("1418377085@qq.com",user.getEmail());
        assertEquals("1418377085",user.getName());
    }

    @Test
    public void findUserByEmail() {
        User user = userService.findUserByEmail("1418377085@qq.com");
        assertNotNull(user);
        assertEquals(1, (long) user.getId());
        assertEquals("1418377085",user.getName());
    }

    @Test
    public void saveUser() {
    }

    @Test
    public void createUser() {
    }
}