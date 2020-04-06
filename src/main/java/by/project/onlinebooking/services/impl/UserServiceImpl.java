package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.DTO.UserDTO;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.mappers.UserMapper;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PassengerService passengerService;
    private final UserMapper mapper = Mappers.getMapper( UserMapper.class );

    public UserServiceImpl(UserRepository userRepository,
                           PassengerServiceImpl passengerService) {
        this.userRepository = userRepository;
        this.passengerService = passengerService;
    }

    @Override
    public UserDTO add(UserDTO user) {
        User userPOJO = mapper.userDtoToUser( user );
        return mapper.userToUserDto( userRepository.save( userPOJO ) );
    }

    @Override
    public UserDTO getById(long id) {
        return mapper.userToUserDto( userRepository.getOne( id ) );
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map( mapper::userToUserDto )
                .collect( Collectors.toList() );
    }

    @Override
    public UserDTO update(UserDTO user) {
        User updatedUser = userRepository.getOne( user.getId() );
        updatedUser.setFirstName( user.getFirstName() );
        updatedUser.setLastName( user.getLastName() );
        updatedUser.setPassword( user.getPassword() );
        updatedUser.setPhone( user.getPhone() );
        return mapper.userToUserDto( userRepository.save( updatedUser ) );
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByUserId( id );
        User user = userRepository.getOne( id );
        userRepository.delete( user );
    }
}
