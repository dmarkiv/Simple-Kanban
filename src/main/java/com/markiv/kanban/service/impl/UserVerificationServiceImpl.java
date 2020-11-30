package com.markiv.kanban.service.impl;

import com.markiv.kanban.entity.User;
import com.markiv.kanban.entity.UserVerification;
import com.markiv.kanban.enums.Status;
import com.markiv.kanban.exception.BadUserVerificationTokenException;
import com.markiv.kanban.exception.UserNotFoundException;
import com.markiv.kanban.exception.VerificationTokenExpiredException;
import com.markiv.kanban.repository.UserRepository;
import com.markiv.kanban.repository.UserVerificationRepository;
import com.markiv.kanban.service.UserVerificationService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVerificationServiceImpl implements UserVerificationService {
  UserVerificationRepository verificationRepository;
  UserRepository userRepository;

  @Autowired
  public UserVerificationServiceImpl(UserVerificationRepository verificationRepository,
                                     UserRepository userRepository) {
    this.verificationRepository = verificationRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void verify(long id, String token) {
    UserVerification verification = verificationRepository.findById(id)
      .orElseThrow(
        () -> new UserNotFoundException("User with id: " + id + " was not found"));
    if (!verification.getToken().equals(token)) {
      throw new BadUserVerificationTokenException("Bad user verification token");
    }
    if (verification.isExpired()) {
      throw new VerificationTokenExpiredException("Verification token expired");
    }

    User user = verification.getUser();
    user.setStatus(Status.ENABLED);
    userRepository.save(user);

    verificationRepository.delete(verification);
  }

  public UserVerification generateUserVerification(User user) {
    return UserVerification.builder()
      .token(UUID.randomUUID().toString())
      .expirationTime(LocalDateTime.now().plusDays(1))
      .user(user)
      .build();
  }
}
