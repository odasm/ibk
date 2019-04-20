package com.javalitterboy.ibk.service;

import com.javalitterboy.ibk.entity.User;

/**
 * @author 14183
 */
public interface UserService {

    public User findUserById(long id);

    public User findUserByEmail(String email);

    public User saveUser(User user);

    public User createUser(User user);
}
