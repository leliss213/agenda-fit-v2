package com.example.agenda_fit_v2.controller.dto;
import jakarta.validation.constraints.NotNull;

public record WorkoutsExercisesDTO(@NotNull Long exerciseId,
                                   double weight,
                                   int repetitions,
                                   int sets) {}
