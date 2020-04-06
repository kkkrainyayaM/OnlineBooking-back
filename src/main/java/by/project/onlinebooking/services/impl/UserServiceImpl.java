package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.exceptions.UserNotFoundException;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User add(User user) {
        return userRepository.save( user );
    }

    @Override
    public User getById(long id) throws UserNotFoundException {
        return userRepository.findById( id )
                .orElseThrow( () -> new UserNotFoundException( id ) );
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) throws UserNotFoundException {
        User updatedUser = userRepository.findById( user.getId() )
                .orElseThrow( () -> new UserNotFoundException( user.getId() ) );
        updatedUser.setFirstName( user.getFirstName() );
        updatedUser.setLastName( user.getLastName() );
        updatedUser.setPassword( user.getPassword() );
        updatedUser.setPhone( user.getPhone() );
        return userRepository.save( updatedUser );
    }

    @Override
    public void delete(long id) throws UserNotFoundException {
        passengerService.deleteByUserId( id );
        User user = userRepository.findById( id )
                .orElseThrow( () -> new UserNotFoundException( id ) );
        userRepository.delete( user );
    }
}
