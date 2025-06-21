package org.oauth.service.impl;


import lombok.RequiredArgsConstructor;
import org.oauth.mapper.UserMapper;
import org.oauth.model.User;
import org.springframework.stereotype.Service;
import org.oauth.repository.UserRepository;
import org.oauth.service.IUserService;
import org.oauth.dto.UserDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;


  @Override
  public List<UserDTO> getAllUsers() {
    return  userRepository.findAll().stream()
        .map(userMapper::toDTO)
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO createUser(UserDTO userDTO) {

    User user = userMapper.toEntity(userDTO);
    User savedUser = userRepository.save(user);
    return userMapper.toDTO(savedUser);
  }

   public Optional<Object> getUserById(String id) {

    return userRepository.findById(id)
        .map(userMapper::toDTO);
  }

  @Override
  public UserDTO updateUser(String id, UserDTO userDTO) {

    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    existingUser.setName(userDTO.getName());
    existingUser.setUsername(userDTO.getUsername());
    existingUser.setPassword(userDTO.getPassword());
    User updatedUser = userRepository.save(existingUser);
    return userMapper.toDTO(updatedUser);
  }

  @Override
  public void deleteUser(String id) {

    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id: " + id);
    }
    userRepository.deleteById(id);
  }
}
