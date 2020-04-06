package by.project.onlinebooking.services;

import by.project.onlinebooking.DTO.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * Add a new user
     *
     * @param user - new User
     * @return added user
     */
    UserDTO add(UserDTO user);

    /**
     * Get a user by ID
     *
     * @param id - user ID
     * @return user
     */
    UserDTO getById(long id);

    /**
     * Get list of all users
     *
     * @return a list of all users
     */
    List<UserDTO> getAll();

    /**
     * Update user
     *
     * @param user - existed user with updated fields
     * @return updated user
     */
    UserDTO update(UserDTO user);

    /**
     * Delete user by ID
     *
     * @param id - user ID
     */
    void delete(long id);
}
