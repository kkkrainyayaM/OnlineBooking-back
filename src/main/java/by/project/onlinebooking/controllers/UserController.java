package by.project.onlinebooking.controllers;

import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
@Api(value = "Online-booking system")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "View a list of all users", response = List.class)
    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @ApiOperation(value = "Get a user by ID")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getById( id );
    }

    @ApiOperation(value = "Add a user")
    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        return userService.add( user );
    }

    @ApiOperation(value = "Update a user")
    @PutMapping("/users")
    public User updateUser(@Valid @RequestBody User user) {
        return userService.update( user );
    }

    @ApiOperation(value = "Delete a user")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") long id) {
        userService.delete( id );
    }


}
