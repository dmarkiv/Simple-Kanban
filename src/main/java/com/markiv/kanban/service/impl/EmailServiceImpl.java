package com.markiv.kanban.service.impl;

import com.markiv.kanban.constant.ContentType;
import com.markiv.kanban.service.EmailService;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

  private JavaMailSender javaMailSender;

  private TemplateEngine templateEngine;

  @Autowired
  public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  @Value("${mail.server.username}")
  private String senderEmailAddress;

  @Override
  public void sendEmailVerification(String receiver, String firstname, String verificationLink) {
    Map<String, Object> model = new HashMap<>();
    model.put("receiverName", firstname);
    model.put("verificationLink", verificationLink);

    String emailContent = templateContent(model, "email-verification");
    sendEmail(receiver, "Please, verify you email", emailContent, ContentType.HTML);
  }

  public void sendEmail(String receiver, String subject, String content, String contentType) {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    try {
      mimeMessageHelper.setFrom(senderEmailAddress);
      mimeMessageHelper.setTo(receiver);
      mimeMessageHelper.setSubject(subject);
      mimeMessage.setContent(content, contentType);
    } catch (MessagingException e) {
      //TODO: handle this properly
    }
    javaMailSender.send(mimeMessage);
  }

  private String templateContent(Map<String, Object> vars, String templateName) {
    Context context = new Context();
    context.setVariables(vars);
    return templateEngine.process("email/" + templateName, context);
  }
}
