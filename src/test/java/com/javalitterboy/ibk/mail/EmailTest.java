package com.javalitterboy.ibk.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    @Autowired
    private Email email;

    @Test
    public void sendValidEmail() throws UnsupportedEncodingException, MessagingException {
        email.sendValidEmail("1026512252@qq.com","12354");
    }
}