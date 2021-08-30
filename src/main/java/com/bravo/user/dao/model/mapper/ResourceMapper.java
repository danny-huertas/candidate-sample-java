package com.bravo.user.dao.model.mapper;

import com.bravo.user.dao.model.User;
import com.bravo.user.model.dto.UserReadDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceMapper {

  private final MapperFacade mapperFacade;

  public ResourceMapper(MapperFacade mapperFacade) {
    this.mapperFacade = mapperFacade;
  }

  public <T extends Collection<User>> List<UserReadDto> convertUsers(final T users){
    return users.stream().map(this::convertUser).collect(Collectors.toList());
  }

  public UserReadDto convertUser(final User user){
      final UserReadDto dto = mapperFacade.map(user, UserReadDto.class);

      String name;
      if (user.getMiddleName() != null && !user.getMiddleName().trim().isEmpty()) {
          name = String.format("%s %s %s", user.getFirstName(), user.getMiddleName(), user.getLastName());
      } else {
          name = String.format("%s %s", user.getFirstName(), user.getLastName());
      }
      dto.setName(name);

      //add auth email and role to user dto
      if (user.getAuth() != null) {
          dto.setEmail(user.getAuth().getEmail());
          dto.setRole(user.getAuth().getRole());
      }
      return dto;
  }
}
