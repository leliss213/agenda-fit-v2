package com.example.agenda_fit_v2.controller;

import com.example.agenda_fit_v2.controller.dto.WorkoutDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutResponseDTO;
import com.example.agenda_fit_v2.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDTO> addWorkout(@RequestBody @Valid WorkoutDTO workoutDTO) {

        WorkoutResponseDTO workout = workoutService.createWorkout(workoutDTO);
        return ResponseEntity.created(URI.create("/workouts/" + workout.id_workout())).body(workout);
    }

    @PatchMapping("/{id_workout}")
    public ResponseEntity<WorkoutResponseDTO> updateWorkout(@RequestBody @Valid WorkoutDTO workoutDTO, @PathVariable UUID id_workout) {

        WorkoutResponseDTO updateWorkout = workoutService.updateWorkout(workoutDTO, id_workout);
        return ResponseEntity.ok(updateWorkout);
    }

    @GetMapping("/user/{id_user}")
    public ResponseEntity<List<WorkoutResponseDTO>> getWorkoutsByUser(@PathVariable UUID id_user) {
        return ResponseEntity.ok(workoutService.getWorkoutsByUser(id_user));
    }
}
