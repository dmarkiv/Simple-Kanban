package com.markiv.kanban.exception;

public class BadUserVerificationTokenException extends RuntimeException {
  public BadUserVerificationTokenException(String message) {
    super(message);
  }
}
