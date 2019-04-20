package com.javalitterboy.ibk.handler;

import com.javalitterboy.ibk.constant.StatusCode;
import com.javalitterboy.ibk.pojo.Result;
import com.javalitterboy.ibk.utils.FastJsonUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 14183
 */
@Component
public class AuthenticationHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result result = new Result(StatusCode.SC_ERROR,"登录失败",null);
        if(e instanceof BadCredentialsException){
            result.setInfo("邮箱号或者密码错误");
        }else if(e instanceof UsernameNotFoundException){
            result.setInfo("邮箱号或者密码错误");
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(FastJsonUtils.convertObjectToJSON(result));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(FastJsonUtils.convertObjectToJSON(new Result(StatusCode.SC_OK,"登录成功",null)));
    }
}
