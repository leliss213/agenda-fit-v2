package com.example.agenda_fit_v2.repository;

import com.example.agenda_fit_v2.entity.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercises, Long> {
}
