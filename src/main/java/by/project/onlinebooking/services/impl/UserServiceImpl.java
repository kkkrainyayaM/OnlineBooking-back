package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.Role;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.PassGenerator;
import by.project.onlinebooking.helpers.PasswordEncoder;
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
        user.setPassword( PasswordEncoder.encode( PassGenerator.generateRandomPassword() ) );
        return userMapper.userToUserDto( userRepository.save( user ) );
    }

    @Override
    public UserDto getById(long id) {
        return userMapper.userToUserDto( userRepository.findById( id ).get() );
    }

    @Override
    public UserDto getByPhone(String phone) {
        return userMapper.userToUserDto( userRepository.findByPhone( phone ) );
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map( userMapper::userToUserDto )
                .collect( Collectors.toList() );
    }

    @Override
    public UserDto update(UserDto newUser) {
        User user = userRepository.findById( newUser.getId() ).get();
        User updatedUser = userRepository.save( userMapper.update( user, newUser ) );
        return userMapper.userToUserDto( updatedUser );
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByUserId( id );
        User user = userRepository.findById( id ).get();
        userRepository.delete( user );
    }

    @Override
    public Role logIn(String phone, String password) {
        User user = userRepository.findByPhoneAndPassword( phone, PasswordEncoder.encode( password ) );
        if( user != null ) {
            return user.getRole();
        }
        else return Role.UNAUTHORIZED;
    }
}
