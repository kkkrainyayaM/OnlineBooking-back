package by.project.onlinebooking.controllers;

import by.project.onlinebooking.DTO.UserDTO;
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
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @ApiOperation(value = "Get a user by ID")
    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable long id) {
        return userService.getById( id );
    }

    @ApiOperation(value = "Add a user")
    @PostMapping("/users")
    public UserDTO addUser(@Valid @RequestBody UserDTO user) {
        return userService.add( user );
    }

    @ApiOperation(value = "Update a user")
    @PutMapping("/users")
    public UserDTO updateUser(@Valid @RequestBody UserDTO user) {
        return userService.update( user );
    }

    @ApiOperation(value = "Delete a user")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") long id) {
        userService.delete( id );
    }


}
