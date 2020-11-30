package com.markiv.kanban.security.service;

import com.markiv.kanban.entity.User;
import com.markiv.kanban.service.UserService;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JwtUserDetailsService implements UserDetailsService {
  UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userService.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User with username: " + username + " was not found");
    }
    return null;
  }
}
