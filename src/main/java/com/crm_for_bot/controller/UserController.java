package com.crm_for_bot.controller;

import com.crm_for_bot.dto.UpdateUserDto;
import com.crm_for_bot.dto.UserDto;
import com.crm_for_bot.entity.User;
import com.crm_for_bot.exception.RecourseNotFoundException;
import com.crm_for_bot.mapper.UserDtoMapper;
import com.crm_for_bot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            UserDto registeredUser = userService.registerUser(userDto);
            return ResponseEntity.ok(registeredUser);
        } catch (RecourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getUser() {
        try {
            List<User> users = userService.getAllUsers();
            List<UserDto> userDtos = users.stream()
                    .map(userDtoMapper::mapToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
        try {
            userService.deleteUserByUsername(username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateByUsername/{username}")
    public ResponseEntity<?> updateUserByUsername(@PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {
        try {
            UpdateUserDto currentUser = userService.updateUserPassword(username, updateUserDto);
            if (currentUser != null) {
                return ResponseEntity.ok(currentUser);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with username " + username + " not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with username " + username + " not found");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user");
        }
    }

}
