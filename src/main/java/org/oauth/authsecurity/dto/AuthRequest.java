package org.oauth.authsecurity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
  @NotBlank
  @Email
  private String username;

  @NotBlank
  @Size(min = 8, max = 20, message = "8 y 10 c")
  private String password;
}
