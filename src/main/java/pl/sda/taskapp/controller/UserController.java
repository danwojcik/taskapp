package pl.sda.taskapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.taskapp.dto.UserDto;
import pl.sda.taskapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;

    @Autowired //wstrzyknięcie przez konstruktor
    public void userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping //tworzenie użytkownika
    public void registerUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @DeleteMapping("/{userId}") //usuwanie użytkownika
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deteleUser(userId);
    }

    @GetMapping
    public List<UserDto> getUser() {
        return userService.getUsers();
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        userService.findUser(userId).ifPresent(oldUser -> userService.updateUser(oldUser, userDto));
    }

    @PatchMapping("/{userPassword}")
    public void updateUserPassword(@PathVariable("userPassword") Long userId, @RequestBody UserDto userDto) {
        userService.findUser(userId).ifPresent(oldUserPassword -> userService.updateUserPassword(userDto.getPassword(), userId));
    }

}
