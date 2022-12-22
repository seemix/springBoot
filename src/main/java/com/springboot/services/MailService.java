package com.springboot.services;

import com.springboot.models.Customer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    public void send(Customer customer) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            mimeMessageHelper.setTo(customer.getEmail());
            mimeMessageHelper.setText("<a href='http://localhost:8080/customers/activate/" + customer.getId() + "'>click to activate</a>", true);
            mimeMessageHelper.setFrom(new InternetAddress("mr.java2022@gmail.com"));
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

