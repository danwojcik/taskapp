package pl.sda.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.taskapp.dto.UserDto;
import pl.sda.taskapp.mapper.UserMapper;
import pl.sda.taskapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //adnotajca beanowa
public class UserService {


    @Deprecated
    private List<UserDto> users = new ArrayList<>();
    private final UserRepository userRepository;
    private final SmsSenderService smsSenderService;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, SmsSenderService smsSenderService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.smsSenderService = smsSenderService;
    }


    //1.rejestracja konta użytkownika
    public void addUser(UserDto userDto) {
        var userToSave = userMapper.mapToUser(userDto);
        userRepository.save(userToSave);
    }

    //2.usuwanie konta użytkownika
    public void deteleUser(Long userId) {
        findUser(userId).ifPresent(users::remove);
    }

    //pobieranie użytkownika
    public Optional<UserDto> findUser(Long userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    //4.pobieranie szczegółów użytkownika
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    //5.aktualizaja danych użytkownika
    public void updateUser(UserDto oldUser, UserDto newUser) {
        users.remove(oldUser);
        users.add(newUser);
    }

    //6.aktualizacja hasła użytkownika
    public void updateUserPassword(String newUserPassword, Long userId) {
        users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(users -> users.setPassword(newUserPassword));
    }
}
