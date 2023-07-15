package com.pokedex.services;

import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public int randomNumber = 0;

    public void sendSimpleMessage(String email) throws MessagingException, MessagingException {

        Random random = new Random();
        randomNumber = random.nextInt(90000000) + 10000000;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(email);
        helper.setTo(email);
        helper.setSubject("Aquí tienes tu token de acceso");

        Context context = new Context();
        context.setVariable("variable", randomNumber);

        String htmlContent = templateEngine.process("token.html", context);

        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    public void sendPasswordRecover(String email) throws MessagingException, MessagingException {
        Random random = new Random();
        randomNumber = random.nextInt(90000000) + 10000000;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("no-reply@example.com");
        helper.setTo(email);
        helper.setSubject("Aquí tienes tu token de acceso");

        Context context = new Context();
        context.setVariable("variable", randomNumber);

        String htmlContent = templateEngine.process("password.html", context);

        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

}

