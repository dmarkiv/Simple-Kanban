package com.markiv.kanban.repository;

import com.markiv.kanban.entity.UserVerification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
  Optional<UserVerification> findById(long id);
}
