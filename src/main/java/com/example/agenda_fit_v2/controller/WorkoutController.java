package com.example.agenda_fit_v2.controller;

import com.example.agenda_fit_v2.controller.dto.WorkoutDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutResponseDTO;
import com.example.agenda_fit_v2.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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

        return ResponseEntity.created(URI.create("/workouts/" + workout.id())).body(workout);
    }
}
