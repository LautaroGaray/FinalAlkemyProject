package org.oauth.authsecurity.service;


import org.oauth.authsecurity.dto.AuthRequest;
import org.oauth.authsecurity.dto.AuthResponse;
import org.oauth.dto.UserDTO;

public interface IAuthService {
    AuthResponse register(UserDTO request) throws Exception;
    AuthResponse authenticate(AuthRequest request);
}