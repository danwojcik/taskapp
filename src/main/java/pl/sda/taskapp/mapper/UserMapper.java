package pl.sda.taskapp.mapper;

import org.springframework.stereotype.Service;
import pl.sda.taskapp.dto.UserDto;
import pl.sda.taskapp.entity.User;

@Service
public class UserMapper {

    public UserDto mapToDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .login(user.getLogin())
                .sex(user.getSex())
                .build();
    }
    public User mapToUser(UserDto userDto) {

        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .sex(userDto.getSex())
                .build();
    }
}
