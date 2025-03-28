package com.example.agenda_fit_v2.repository;

import com.example.agenda_fit_v2.entity.WorkoutsExercises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutsExercisesRepository extends JpaRepository<WorkoutsExercises, Long> {
}
