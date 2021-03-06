package com.javalitterboy.ibk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 14183
 */
@Controller
@RequestMapping("/")
public class PageController extends BaseController {

    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = {"/product"})
    public String product(){
        return "product";
    }

    @GetMapping(value = {"/about"})
    public String about(){
        return "about";
    }
}
