package com.markiv.kanban.dto;

import com.markiv.kanban.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserRegistrationDto {
  private String firstName;
  private String secondName;
  private String email;
  private String username;
  private String password;

  public User toEntity() {
    return User.builder()
      .firstName(firstName)
      .secondName(secondName)
      .email(email)
      .username(username)
      .password(password)
      .build();
  }
}
