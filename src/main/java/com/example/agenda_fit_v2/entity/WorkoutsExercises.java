package com.example.agenda_fit_v2.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "workouts_exercises")
public class WorkoutsExercises implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_workout_exercise")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_workout")
    @Column(name = "")
    private Workouts workout;

    private double weight;

    private int repetitions;

    private int sets;


}
