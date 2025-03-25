package com.example.agenda_fit_v2.controller;

import com.example.agenda_fit_v2.controller.dto.ExerciseDTO;
import com.example.agenda_fit_v2.entity.Exercises;
import com.example.agenda_fit_v2.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping()
    public ResponseEntity<Exercises> addExercise(@RequestBody @Valid ExerciseDTO exercisesDto) {
        var exercise = exerciseService.createExercise(exercisesDto);

        return ResponseEntity.ok(exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
