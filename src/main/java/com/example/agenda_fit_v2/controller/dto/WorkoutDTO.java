package com.example.agenda_fit_v2.controller.dto;

import com.example.agenda_fit_v2.entity.WorkoutsExercises;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record WorkoutDTO(@NotBlank String title,
                         String description,
                         LocalDate date,
                         double hours,
                         @NotNull List<WorkoutsExercisesDTO> workoutsExercises,
                         int type,
                         @NotNull UUID userId
                         ) {

//    public Workouts workout() {
//        return new Workouts(title, description, date, hours, workoutsExercises, tipo, userId);
//    }
}
