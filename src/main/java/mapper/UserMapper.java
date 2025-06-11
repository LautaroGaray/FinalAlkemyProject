package mapper;

import dto.UserDTO;
import enums.Role;
import model.User;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

  default UserDTO toDTO(User user) {
    if (user == null) {
      return null;
    }
    Set<String> roles = null;
    if (user.getRoles() != null) {
      roles = user.getRoles()
              .stream()
              .map(Role::getName) // Asegúrate de que Role tenga un método getName()
              .collect(Collectors.toSet());
    }

    return UserDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(roles)
            .build();
  }

  default User toEntity(UserDTO dto) {
    if (dto == null) {
      return null;
    }

    Set<Role> roles = null;
    if (dto.getRoles() != null) {
      roles = dto.getRoles()
              .stream()
              .map(roleString -> Role.valueOf(roleString.toUpperCase())) // Convertir a mayúsculas
              .collect(Collectors.toSet());
    }

    return User.builder()
            .id(dto.getId())
            .name(dto.getName())
            .username(dto.getUsername())
            .password(dto.getPassword())
            .roles(roles)
            .active(true) // Asignar valor por defecto
            .build();
  }
}





