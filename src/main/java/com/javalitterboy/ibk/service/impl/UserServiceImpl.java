package com.javalitterboy.ibk.service.impl;

import com.javalitterboy.ibk.entity.User;
import com.javalitterboy.ibk.exception.ServiceException;
import com.javalitterboy.ibk.service.UserService;
import com.mysql.jdbc.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 14183
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService, UserDetailsService {

    private static final int PASS_MAX_LEN = 18;
    private static final int PASS_MIN_LEN = 6;

    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(BCryptPasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Override
    public User findUserById(long id) {
        return commonDao.get(User.class,id);
    }

    @Override
    public User findUserByEmail(String email) {
        String hql = "obj.email = :email";
        return commonDao.findUniqueByHql(User.class, hql, "email", email);
    }

    @Override
    public User saveUser(User user) {
        String username = user.getName();
        String email = user.getEmail();
        if (StringUtils.isNullOrEmpty(email)) {
            throw new ServiceException("邮箱必填！");
        }
        if (StringUtils.isNullOrEmpty(username)) {
            throw new ServiceException("用户名必填!");
        }
        User existUser = findUserByEmail(email);
        if (null != existUser && !user.getId().equals(existUser.getId())) {
            throw new ServiceException("该email已被使用!");
        }
        commonDao.save(user, user);
        return user;
    }

    @Override
    public User createUser(User user) {
        validatePassword(user.getPassword());
        user.setName(user.getEmail().substring(0,user.getEmail().indexOf('@')));
        user.setPassword(encoder.encode(user.getPassword()));
        saveUser(user);
        return user;
    }

    /**
     * 校验密码
     *
     * @param password 用户密码
     */
    private void validatePassword(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new ServiceException("密码不能为空!");
        }
        if (password.length() > PASS_MAX_LEN || password.length() < PASS_MIN_LEN) {
            throw new ServiceException("密码长度只能在8到16之间！");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = findUserByEmail(email);
        if(null==user){
            String message = "用户不存在";
            throw new UsernameNotFoundException(message);
        }
        return user;
    }
}
