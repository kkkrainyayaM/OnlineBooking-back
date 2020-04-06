package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.mappers.UserMapper;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PassengerService passengerService;

    public UserServiceImpl(UserRepository userRepository,
                           PassengerServiceImpl passengerService) {
        this.userRepository = userRepository;
        this.passengerService = passengerService;
    }

    @Override
    public UserDto add(UserDto user) {
        User userPOJO = UserMapper.INSTANCE.userDtoToUser( user );
        return UserMapper.INSTANCE.userToUserDto( userRepository.save( userPOJO ) );
    }

    @Override
    public UserDto getById(long id) {
        return UserMapper.INSTANCE.userToUserDto( userRepository.getOne( id ) );
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map( UserMapper.INSTANCE::userToUserDto )
                .collect( Collectors.toList() );
    }

    @Override
    public UserDto update(UserDto user) {
        User updatedUser = userRepository.getOne( user.getId() );
        updatedUser.setFirstName( user.getFirstName() );
        updatedUser.setLastName( user.getLastName() );
        updatedUser.setPassword( user.getPassword() );
        updatedUser.setPhone( user.getPhone() );
        return UserMapper.INSTANCE.userToUserDto( userRepository.save( updatedUser ) );
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByUserId( id );
        User user = userRepository.getOne( id );
        userRepository.delete( user );
    }
}
