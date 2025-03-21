package com.example.agenda_fit_v2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "workouts_exercises")
@Getter
@Setter
public class WorkoutsExercises implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_workout_exercise")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_workout")
    private Workouts workout;

    @ManyToOne
    @JoinColumn(name = "id_exercise")
    private Exercises exercise;

    @Column(name = "weight")
    private double weight;

    @Column(name = "repetitions")
    private int repetitions;

    @Column(name = "sets")
    private int sets;

    public WorkoutsExercises() {
    }

    public WorkoutsExercises(Workouts workout, Exercises exercise, double weight, int repetitions, int sets) {
        this.workout = workout;
        this.exercise = exercise;
        this.weight = weight;
        this.repetitions = repetitions;
        this.sets = sets;
    }
}
