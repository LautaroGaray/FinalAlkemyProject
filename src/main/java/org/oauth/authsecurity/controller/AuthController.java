package org.oauth.authsecurity.controller;


import org.oauth.authsecurity.dto.AuthRequest;
import org.oauth.authsecurity.dto.AuthResponse;
import org.oauth.authsecurity.service.IAuthService;
import org.oauth.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final IAuthService IAuthService;


  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserDTO request) throws Exception {
    return ResponseEntity.ok(IAuthService.register(request));}


  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
    return ResponseEntity.ok(IAuthService.authenticate(request));   }}
