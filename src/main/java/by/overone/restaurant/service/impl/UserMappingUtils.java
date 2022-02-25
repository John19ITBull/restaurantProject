package by.overone.restaurant.service.impl;

import by.overone.restaurant.entity.User;
import by.overone.restaurant.entity.dto.UserDTO;
import by.overone.restaurant.service.impl.UserService;
import by.overone.restaurant.service.IMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMappingUtils implements IMappingUtils<UserDTO, User> {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO mapToDto(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getUsername());
        userDTO.setRole(entity.getRole());
        userDTO.setBalance(entity.getBalance());
        userDTO.setName(entity.getDetail().getName());
        userDTO.setSurname(entity.getDetail().getSurname());
        userDTO.setPhoneNumber(entity.getDetail().getPhoneNumber());
        userDTO.setEmail(entity.getDetail().getEmail());

        return userDTO;
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {
        return userService.findById(userDTO.getId());
    }
}
