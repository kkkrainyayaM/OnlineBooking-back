package by.project.onlinebooking.services;

import by.project.onlinebooking.entities.User;

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
     */
    User getById(long id);

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
     */
    User update(User user);

    /**
     * Delete user by ID
     *
     * @param id - user ID
     */
    void delete(long id);
}
