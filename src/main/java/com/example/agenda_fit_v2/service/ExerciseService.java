package com.example.agenda_fit_v2.service;

import com.example.agenda_fit_v2.controller.dto.ExerciseDTO;
import com.example.agenda_fit_v2.entity.Exercises;
import com.example.agenda_fit_v2.repository.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public Exercises createExercise(ExerciseDTO dtoExercise) {
        return exerciseRepository.save(dtoExercise.exercises());
    }
}
