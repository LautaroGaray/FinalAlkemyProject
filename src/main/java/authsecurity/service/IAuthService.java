package authsecurity.service;


import authsecurity.dto.AuthRequest;
import authsecurity.dto.AuthResponse;
import dto.UserDTO;

public interface IAuthService {
    AuthResponse register(UserDTO request) throws Exception;
    AuthResponse authenticate(AuthRequest request);
}