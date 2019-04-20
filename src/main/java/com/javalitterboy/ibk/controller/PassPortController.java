package com.javalitterboy.ibk.controller;

import com.javalitterboy.ibk.entity.User;
import com.javalitterboy.ibk.mail.Email;
import com.javalitterboy.ibk.pojo.Result;
import com.javalitterboy.ibk.service.UserService;
import com.javalitterboy.ibk.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalTime;

/**
 * @author 14183
 */
@Controller
@RequestMapping("/passport")
public class PassPortController extends BaseController {

    private final Email email;
    private final UserService userService;

    public PassPortController(Email email, UserService userService) {
        this.email = email;
        this.userService = userService;
    }

    @PostMapping("/submit")
    @ResponseBody
    public Result submit(@RequestParam("email")String email, @RequestParam("captcha")String captcha, HttpServletRequest request){
        boolean isCheck = (boolean)request.getSession().getAttribute("is_check_valid_code");
        String validCode = (String)request.getSession().getAttribute("valid_code");
        if(null==validCode) {
            return new Result(-1,"验证码不存在",null);
        }
        if(isCheck) {
            return new Result(-2,"验证码已经使用",null);
        }
        if(!validCode.equalsIgnoreCase(captcha)) {
            return new Result(-3,"验证码错误",null);
        }
        // 验证邮箱是否使用
        if(null!=userService.findUserByEmail(email)){
            return new Result(-4,"邮箱已经被使用",null);
        }
        String emailCode = Util.random_str(6);
        request.getSession().setAttribute("email_code",emailCode);
        // 有效时间10分钟
        request.getSession().setAttribute("email_code_expire", LocalTime.now().plusMinutes(10));
        try {
            this.email.sendValidEmail(email,emailCode);
            return new Result(1,"邮件验证码已经发送",null);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Result(-5,"邮件验证码发送失败",null);
        }
    }

    @PostMapping("/sendnum")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result sendnum(@RequestParam("email")String email, @RequestParam("password")String password,@RequestParam("megnum")String emailCode, HttpServletRequest request){
        LocalTime expireTime = (LocalTime)request.getSession().getAttribute("email_code_expire");
        if(null==expireTime||expireTime.isBefore(LocalTime.now())){

            return new Result(-1,"邮件验证码过期",null);
        }
        String emailCodeSession = (String)request.getSession().getAttribute("email_code");
        if(null==emailCodeSession){
            return new Result(-2,"邮件验证码不存在",null);
        }
        if(!emailCodeSession.equalsIgnoreCase(emailCode)){
            return new Result(-2,"邮件验证码错误",null);
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.createUser(user);
        return new Result(1,"注册成功",null);
    }

    @GetMapping("/register")
    public String register(){
        return "passport/register";
    }

    @GetMapping("/loginFrame")
    public String loginFrame(){
        return "passport/loginFrame";
    }

    @RequestMapping("/login_submit")
    @ResponseBody
    public Result loginSubmit(){
        return new Result(1,"登录成功",null);
    }
}
