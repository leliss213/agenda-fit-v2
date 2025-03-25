package com.example.agenda_fit_v2.controller.dto;

import com.example.agenda_fit_v2.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserDTO(@NotBlank String login,
                     @NotBlank String password,
                     @NotBlank @Email String email,
                     @NotNull LocalDate birth_date,
                     double height,
                     double weight){

    public Users user(){
        return new Users(login, password, email, birth_date, height, weight);
    }
}
