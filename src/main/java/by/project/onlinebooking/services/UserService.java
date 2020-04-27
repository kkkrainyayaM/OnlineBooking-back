package by.project.onlinebooking.services;

import by.project.onlinebooking.dto.LoginDto;
import by.project.onlinebooking.dto.UserDto;

import java.util.List;

public interface UserService {

    /**
     * Add a new user
     *
     * @param user - new User
     * @return added user
     */
    UserDto add(UserDto user);

    /**
     * Get a user by ID
     *
     * @param id - user ID
     * @return user
     */
    UserDto getById(long id);

    /**
     * Get a user by phone
     *
     * @param phone - user phone
     * @return user
     */
    UserDto getByPhone(String phone);

    /**
     * Get list of all users
     *
     * @return a list of all users
     */
    List<UserDto> getAll();

    /**
     * Update user
     *
     * @param user - existed user with updated fields
     * @return updated user
     */
    UserDto update(UserDto user);

    /**
     * Delete user by ID
     *
     * @param id - user ID
     */
    void delete(long id);

    /**
     * LogIn user by phone and password
     *
     * @param loginDto - phone and password
     * @return user
     */
    UserDto logIn(LoginDto loginDto);
}
