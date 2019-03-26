package com.javalitterboy.ibk.controller;

import com.javalitterboy.ibk.utils.ValidImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 14183
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    private final ValidImage validImage;

    public CommonController(ValidImage validImage) {
        this.validImage = validImage;
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
       BufferedImage image = validImage.getValidImage();
       request.getSession().setAttribute("valid_code",validImage.getText());
       request.getSession().setAttribute("is_check_valid_code",false);
        //图形写给浏览器
        response.setContentType("image/jpeg");
        try {
            ImageIO.write(image, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
