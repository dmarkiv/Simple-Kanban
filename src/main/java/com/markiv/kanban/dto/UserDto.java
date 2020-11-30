package com.markiv.kanban.dto;

import com.markiv.kanban.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private long id;
  private String firstName;
  private String secondName;
  private String username;
  private String email;

  public static UserDto fromEntity(User user) {
    return new UserDto(user.getId(), user.getFirstName(), user.getSecondName(), user.getUsername(), user.getEmail());
  }
}
