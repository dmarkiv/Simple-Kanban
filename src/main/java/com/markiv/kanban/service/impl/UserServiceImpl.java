package com.markiv.kanban.service.impl;

import com.markiv.kanban.entity.User;
import com.markiv.kanban.repository.UserRepository;
import com.markiv.kanban.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
                         BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }


  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User save(User s) {
    return userRepository.save(s);
  }

  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
