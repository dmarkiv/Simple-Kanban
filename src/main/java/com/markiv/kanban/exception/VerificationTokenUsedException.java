package com.markiv.kanban.exception;

public class VerificationTokenUsedException extends RuntimeException {
  public VerificationTokenUsedException(String message) {
    super(message);
  }
}
