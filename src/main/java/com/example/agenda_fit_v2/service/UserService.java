package com.example.agenda_fit_v2.service;

import com.example.agenda_fit_v2.controller.dto.UserDTO;
import com.example.agenda_fit_v2.entity.Users;
import com.example.agenda_fit_v2.exception.UserAlredyExistException;
import com.example.agenda_fit_v2.exception.UserDoesNotExistException;
import com.example.agenda_fit_v2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    @Transactional
    public Users updateUser(UUID userId, UserDTO dtoUser) {

        Users userDb = userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException(userId));

        var userExist = userRepository.findByLoginOrEmailExcludingId(dtoUser.login(), dtoUser.email(), userId);
        if (userExist.isPresent()) {
            throw new UserAlredyExistException();
        }

        userDb.setLogin(dtoUser.login());
        userDb.setEmail(dtoUser.email());
        userDb.setPassword(dtoUser.password());
        userDb.setWeight(dtoUser.weight());
        userDb.setHeight(dtoUser.height());
        userDb.setBirth_date(dtoUser.birth_date());

        return userRepository.save(userDb);
    }
}
