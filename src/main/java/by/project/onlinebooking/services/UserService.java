package by.project.onlinebooking.services;

import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    /**
     * Add a new user
     *
     * @param user - new User
     * @return added user
     */
    User add(User user);

    /**
     * Get a user by ID
     *
     * @param id - user ID
     * @return user
     * @throws UserNotFoundException if user doesn't exist
     */
    User getById(long id) throws UserNotFoundException;

    /**
     * Get list of all users
     *
     * @return a list of all users
     */
    List<User> getAll();

    /**
     * Update user
     *
     * @param user - existed user with updated fields
     * @return updated user
     * @throws UserNotFoundException if user doesn't exist
     */
    User update(User user) throws UserNotFoundException;

    /**
     * Delete user by ID
     *
     * @param id - user ID
     * @throws UserNotFoundException if user doesn't exist
     */
    void delete(long id) throws UserNotFoundException;
}
