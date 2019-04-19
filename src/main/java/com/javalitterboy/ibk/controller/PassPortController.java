package com.javalitterboy.ibk.controller;

import com.javalitterboy.ibk.mail.Email;
import com.javalitterboy.ibk.mail.MailEntity;
import com.javalitterboy.ibk.pojo.Result;
import com.javalitterboy.ibk.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 14183
 */
@Controller
@RequestMapping("/passport")
public class PassPortController {

    private final Email email;

    public PassPortController(Email email) {
        this.email = email;
    }

    @RequestMapping("/submit")
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
        String emailCode = Util.random_str(6);
        request.getSession().setAttribute("email_code",emailCode);
        // 有效时间10分钟
        request.getSession().setAttribute("email_code_expire", LocalTime.now().plusMinutes(10));
        // 验证邮箱是否使用
        try {
            this.email.sendValidEmail(email,emailCode);
            return new Result(1,"邮件验证码已经发送",null);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Result(-1,"邮件验证码发送失败",null);
        }
    }

    @RequestMapping("/sendnum")
    @ResponseBody
    public Result sendnum(){
        return new Result(1,"注册成功",null);
    }
}
