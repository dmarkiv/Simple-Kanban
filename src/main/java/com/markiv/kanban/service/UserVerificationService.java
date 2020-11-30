package com.markiv.kanban.service;

import com.markiv.kanban.entity.User;
import com.markiv.kanban.entity.UserVerification;

public interface UserVerificationService {
  void verify(long id, String token);

  UserVerification generateUserVerification(User user);
}
