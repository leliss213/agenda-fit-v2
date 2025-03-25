package com.example.agenda_fit_v2.controller;

import com.example.agenda_fit_v2.controller.dto.UserDTO;
import com.example.agenda_fit_v2.entity.Users;
import com.example.agenda_fit_v2.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Users> createUser(@RequestBody @Valid UserDTO userDTO) {
        var User = userService.createUser(userDTO);
        return ResponseEntity.ok(User);
    }
}
