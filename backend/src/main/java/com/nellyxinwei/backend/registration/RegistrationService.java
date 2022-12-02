package com.nellyxinwei.backend.registration;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.nellyxinwei.backend.appuser.AppUser;
import com.nellyxinwei.backend.appuser.AppUserRole;
import com.nellyxinwei.backend.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final AppUserService appUserService;
  private final EmailValidator emailValidator;

  public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.getEmail());

    if (!isValidEmail) {
      throw new IllegalStateException("email not valid");
    }

    return appUserService.signUpUser(

        new AppUser(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            request.getPassword(),
            AppUserRole.USER));
  }
}
