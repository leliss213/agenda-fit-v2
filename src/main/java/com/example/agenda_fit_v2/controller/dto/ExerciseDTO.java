package com.example.agenda_fit_v2.controller.dto;

import com.example.agenda_fit_v2.entity.ExerciseType;
import com.example.agenda_fit_v2.entity.Exercises;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExerciseDTO(@NotBlank String name,
                          @NotNull ExerciseType exerciseType) {

    public Exercises exercises() {
        return new Exercises(name, exerciseType);
    }
}
