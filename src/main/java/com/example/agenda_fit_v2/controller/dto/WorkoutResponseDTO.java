package com.example.agenda_fit_v2.controller.dto;

import java.util.List;
import java.util.UUID;

public record WorkoutResponseDTO<T>(UUID id_workout, String title, String description, List<T> workoutsExercises, UUID user_id) {}