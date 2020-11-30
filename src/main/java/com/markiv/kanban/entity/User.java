package com.markiv.kanban.entity;

import com.markiv.kanban.enums.Status;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue()
  Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "second_name")
  private String secondName;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "user_name", unique = true)
  private String username;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "status")
  private Status status;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private UserVerification userVerification;
}
