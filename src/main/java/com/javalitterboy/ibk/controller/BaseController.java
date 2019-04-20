package com.javalitterboy.ibk.controller;

import com.javalitterboy.ibk.constant.StatusCode;
import com.javalitterboy.ibk.exception.ServiceException;
import com.javalitterboy.ibk.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 14183
 */
public class BaseController {

    @ExceptionHandler
    @ResponseBody
    public Result serviceExceptionHandler(Exception e){
        if(e instanceof ServiceException){
            return new Result(((ServiceException)e).getCode(),e.getMessage(),null);
        }
        return new Result(StatusCode.SC_ERROR,e.getMessage(),null);
    }

    public void test(){
        throw new ServiceException("ASDFASC",StatusCode.SC_ERROR);
    }
}
