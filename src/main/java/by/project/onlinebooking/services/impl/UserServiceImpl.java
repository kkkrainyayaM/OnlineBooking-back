package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.LoginDto;
import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.PasswordEncoder;
import by.project.onlinebooking.mappers.UserMapper;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PassengerService passengerService;
    private final UserMapper userMapper;

    @Override
    public UserDto add(UserDto userDto) {
        log.info("Save user: {}", userDto);
        val user = userMapper.userDtoToUser(userDto);
        val savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public UserDto getById(long id) {
        log.info("UserId = {}", id);
        User user = userRepository.findById(id).get();
        log.info("Get user by id :{}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getByPhone(String phone) {
        log.info("User's phone = {}", phone);
        User user = userRepository.findByPhone(phone);
        log.info("Get user by phone :{}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        val list = userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
        log.info("Get list of users: {}", list);
        return list;
    }

    @Override
    public UserDto update(UserDto newUser) {
        User user = userRepository.save(userMapper.userDtoToUser(newUser));
        log.info("Update user: {}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void delete(long id) {
        log.info("User id = {}", id);
        passengerService.deleteByUserId(id);
        log.info("Deleted passenger by userId");
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        log.info("Deleted user by id = {}", id);
    }

    @Override
    public UserDto logIn(LoginDto loginDto) {
        log.info("Login: {}", loginDto);
        return userMapper.userToUserDto(userRepository.findByPhoneAndPassword(loginDto.getPhone(),
                PasswordEncoder.encode(loginDto.getPassword())));
    }
}
