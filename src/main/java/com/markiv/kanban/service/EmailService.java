package com.markiv.kanban.service;

public interface EmailService {
void sendEmailVerification(String email, String firstname, String verificationLink);
}
