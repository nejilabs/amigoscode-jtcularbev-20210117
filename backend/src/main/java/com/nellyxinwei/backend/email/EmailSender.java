package com.nellyxinwei.backend.email;

public interface EmailSender {
  void send(String to, String email);
}
