package com.markiv.kanban.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

//@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.token.validity-time}")
  private Long validityTime;

  public String createToken(String username) {
    Claims claims = Jwts.claims().setSubject(username);
    return null;
  }

//  public Authentication getAuthentication(String token) {
//
//  }
//
//  public String getUsername(String token) {
//
//  }
//
//  public boolean validateToken(String token) {
//
//  }


}
