package com.markiv.kanban.service;

import com.markiv.kanban.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  List<User> findAll();

  User save(User s);

  Optional<User> findById(Long id);

  Optional<User> findByUsername(String username);

  void deleteById(Long id);
}
