package org.oauth.authsecurity.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oauth.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.oauth.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.debug("Intentando cargar usuario por nombre: {}", username);


    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> {
          log.error("Usuario no encontrado con nombre: {}", username);
          return new UsernameNotFoundException("User not found with username: " + username);

        });

    log.info("Usuario cargado exitosamente: {}", username);
    user.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .forEach(authority -> log.info("User authority en custom user details: {}", authority));
    return user;
  }
}