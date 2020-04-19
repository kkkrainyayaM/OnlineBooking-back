package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.mappers.UserMapper;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PassengerService passengerService;
    private final UserMapper userMapper;

    @Override
    public UserDto add(UserDto newUser) {
        User user = userMapper.userDtoToUser( newUser );
        return userMapper.userToUserDto( userRepository.save( user ) );
    }

    @Override
    public UserDto getById(long id) {
        return userMapper.userToUserDto( userRepository.findById( id ).get() );
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map( userMapper::userToUserDto )
                .collect( Collectors.toList() );
    }

    @Override
    public UserDto update(UserDto user) {
        User updatedUser = userRepository.findById( user.getId() ).get();
        updatedUser.setFirstName( user.getFirstName() );
        updatedUser.setLastName( user.getLastName() );
        updatedUser.setPhone( user.getPhone() );
        return userMapper.userToUserDto( userRepository.save( updatedUser ) );
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByUserId( id );
        User user = userRepository.findById( id ).get();
        userRepository.delete( user );
    }
}
