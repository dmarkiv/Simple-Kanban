package com.markiv.kanban.service;

import com.markiv.kanban.dto.UserRegistrationDto;

public interface SecurityService {
  public void signUp(UserRegistrationDto registrationDto);
}
