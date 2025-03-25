package com.example.agenda_fit_v2.service;

import com.example.agenda_fit_v2.controller.dto.UserDTO;
import com.example.agenda_fit_v2.entity.Users;
import com.example.agenda_fit_v2.exception.UserAlredyExistException;
import com.example.agenda_fit_v2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    @Transactional
    public Users createUser(UserDTO dtoUser) {

        var userDb = userRepository.findByLoginOrEmail(dtoUser.login(), dtoUser.email());
        if (userDb.isPresent()) {
            throw new UserAlredyExistException();
        }
        
        return userRepository.save(dtoUser.user());
    }

}
