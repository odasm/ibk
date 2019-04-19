package com.javalitterboy.ibk.controller;

import com.javalitterboy.ibk.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/passport")
public class PassPortController {

    @RequestMapping("/submit")
    @ResponseBody
    public Result submit(@RequestParam("email")String email, @RequestParam("captcha")String captcha, HttpServletRequest request){
        boolean is_check = (boolean)request.getSession().getAttribute("is_check_valid_code");
        String valid_code = (String)request.getSession().getAttribute("valid_code");
        if(null==valid_code)
            return new Result(-1,"验证码不存在",null);
        if(is_check)
            return new Result(-2,"验证码已经使用",null);
        if(!valid_code.equalsIgnoreCase(captcha))
            return new Result(-3,"验证码错误",null);
        // 验证邮箱是否使用
        return new Result(1,"邮件验证码已经发送",null);
    }

    @RequestMapping("/sendnum")
    @ResponseBody
    public Result sendnum(){
        return new Result(1,"注册成功",null);
    }
}
