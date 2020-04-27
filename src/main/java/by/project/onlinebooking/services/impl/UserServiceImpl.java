package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.LoginDto;
import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.PassGenerator;
import by.project.onlinebooking.helpers.PasswordEncoder;
import by.project.onlinebooking.mappers.UserMapper;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public UserDto add(UserDto newUser) {
        User user = userMapper.userDtoToUser(newUser);
        log.info("User from userMapper: {}", user);
        user.setPassword(PasswordEncoder.encode(PassGenerator.generateRandomPassword()));
        log.info("Generated password for user");
        User userWithId = userRepository.save(user);
        log.info("Saved user : {}", user);
        return userMapper.userToUserDto(userWithId);
    }

    @Override
    public UserDto getById(long id) {
        log.info("UserId = {}", id);
        User user = userRepository.findById(id).get();
        log.info("User by id :{}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getByPhone(String phone) {
        log.info("User phone = {}", phone);
        User user = userRepository.findByPhone(phone);
        log.info("User by phone :{}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> list = userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
        log.info("List of users: {}", list);
        return list;
    }

    @Override
    public UserDto update(UserDto newUser) {
        User user = userRepository.save(userMapper.userDtoToUser(newUser));
        log.info("Updated user: {}", user);
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
        log.info("LoginDto: {}", loginDto);
        return userMapper.userToUserDto(userRepository.findByPhoneAndPassword(loginDto.getPhone(),
                PasswordEncoder.encode(loginDto.getPassword())));
    }
}
