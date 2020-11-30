package com.markiv.kanban.controller;

import com.markiv.kanban.dto.UserRegistrationDto;
import com.markiv.kanban.service.SecurityService;
import com.markiv.kanban.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/security")
public class SecurityController {
  @Autowired
  SecurityService securityService;
  @Autowired
  UserVerificationService userVerificationService;

  @PostMapping("/signup")
  public ResponseEntity<String> register(@RequestBody UserRegistrationDto registrationDto) {
    securityService.signUp(registrationDto);
    return ResponseEntity.status(HttpStatus.CREATED).body("created");
  }

  @GetMapping("/verify")
  public ResponseEntity verify(@RequestParam long id, @RequestParam String token) {
    userVerificationService.verify(id, token);
    return ResponseEntity.ok().build();
  }

//  @PostMapping("/authenticate")
//  public ResponseEntity<>
}
