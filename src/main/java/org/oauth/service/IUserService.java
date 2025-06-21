package org.oauth.service;



import org.oauth.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
  UserDTO createUser(UserDTO user);
  List<UserDTO> getAllUsers();
  UserDTO updateUser(String id, UserDTO user);
  void  deleteUser(String id);

  Optional<Object> getUserById(String id);
}
