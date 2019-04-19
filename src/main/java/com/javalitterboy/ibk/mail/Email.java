package com.javalitterboy.ibk.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author 14183
 */
@Component
public class Email {

    private final JavaMailSender mailSender;

    private final ThymeleafViewResolver thymeleafViewResolver;

    @Value("${spring.mail.username}")
    private String fromAccount;
    @Value("${spring.mail.name}")
    private String fromAccountName;

    public Email(JavaMailSender mailSender, ThymeleafViewResolver thymeleafViewResolver) {
        this.mailSender = mailSender;
        this.thymeleafViewResolver = thymeleafViewResolver;
    }

    public void sendValidEmail(String email,String emailCode) throws MessagingException,UnsupportedEncodingException{


        MailEntity entity = new MailEntity();
        entity.addTo(email);
        entity.setSubject("i记账验证码");
        Map<String,Object> values = new HashMap<>(3);
        values.put("username",email);
        values.put("validcode",emailCode);
        values.put("expire","10分钟");
        entity.setValues(values);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromAccount,fromAccountName);
        helper.setTo(entity.getTos().toArray(new String[0]));
        helper.setCc(entity.getCcs().toArray(new String[0]));
        helper.setBcc(entity.getBccs().toArray(new String[0]));
        helper.setSubject(entity.getSubject());
        Context ctx = new Context(Locale.CHINA);
        Map<String,Object> map = entity.getValues();
        map.keySet().forEach(key ->ctx.setVariable(key, map.get(key)));
        String text = thymeleafViewResolver.getTemplateEngine().process("email/valid_email.html",ctx);
        helper.setText(text, true);
        Resource ibk = new ClassPathResource("static/images/LOGO2.png");
        helper.addInline("ibk",ibk);
        for(String file:entity.getFiles()){
            File f = new File(file);
            helper.addAttachment(f.getName(), f);
        }
        mailSender.send(mimeMessage);
    }
}
