package com.nellyxinwei.backend.registration;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
  public String register(RegistrationRequest request) {
    return "works";
  }
}
