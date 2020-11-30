package com.markiv.kanban.service.impl;

import com.markiv.kanban.dto.UserRegistrationDto;
import com.markiv.kanban.entity.User;
import com.markiv.kanban.entity.UserVerification;
import com.markiv.kanban.enums.Status;
import com.markiv.kanban.repository.UserRepository;
import com.markiv.kanban.service.EmailService;
import com.markiv.kanban.service.SecurityService;
import com.markiv.kanban.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
  UserVerificationService userVerificationService;
  UserRepository userRepository;
  PasswordEncoder passwordEncoder;
  EmailService emailService;

  @Autowired
  public SecurityServiceImpl(UserVerificationService userVerificationService,
                             UserRepository userRepository,
                             PasswordEncoder passwordEncoder, EmailService emailService) {
    this.userVerificationService = userVerificationService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.emailService = emailService;
  }


  @Override
  public void signUp(UserRegistrationDto registrationDto) {
    User user = registrationDto.toEntity();
    user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
    user.setStatus(Status.NOT_ACTIVE);

    UserVerification userVerification = userVerificationService.generateUserVerification(user);
    user.setUserVerification(userVerification);
    userRepository.save(user);

    String vericationLink =
      "localhost:8080/verify?id=" + user.getId() + "&token=" + user.getUserVerification().getToken();
    emailService.sendEmailVerification(user.getEmail(), user.getFirstName(), vericationLink);
  }
}
