package com.example.agenda_fit_v2.controller.dto;


import java.util.List;
import java.util.UUID;

public record WorkoutResponseDTO(UUID id, String title, String description, List<WorkoutsExercisesResponseDTO> workoutsExercises) {}