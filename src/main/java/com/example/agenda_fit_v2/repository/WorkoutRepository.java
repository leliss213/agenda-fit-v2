package com.example.agenda_fit_v2.repository;

import com.example.agenda_fit_v2.entity.Workouts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkoutRepository extends JpaRepository<Workouts, UUID> {
    //List<Workouts> findByUser_IdUser(UUID idUser);
}
