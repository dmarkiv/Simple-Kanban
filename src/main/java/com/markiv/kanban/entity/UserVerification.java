package com.markiv.kanban.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "user_verification")
@Builder
@NoArgsConstructor
public class UserVerification {
  @Id
  @GeneratedValue
  Long id;
  @Column(name = "token")
  String token;
  @Column(name = "expiration_time")
  LocalDateTime expirationTime;
  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @MapsId
  User user;

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expirationTime);
  }

  @PreRemove
  private void removeUserVerificationFromUser() {
    user.setUserVerification(null);
  }
}