package com.javalitterboy.ibk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 14183
 */
@Controller
@RequestMapping("/about")
public class AboutController extends BaseController {

    @GetMapping(value = {"/thanks"})
    public String aboutThanks(){
        return "about/thanks";
    }


    @GetMapping(value = {"/cooperation"})
    public String aboutCooperation(){
        return "about/cooperation";
    }

    @GetMapping(value = {"/manage"})
    public String aboutManage(){
        return "about/manage";
    }

    @GetMapping(value = {"/link"})
    public String aboutLink(){
        return "about/link";
    }
}
