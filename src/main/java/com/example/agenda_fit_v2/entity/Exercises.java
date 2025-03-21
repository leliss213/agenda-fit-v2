package com.example.agenda_fit_v2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "exercises")
@Getter
@Setter
public class Exercises {

    @Id
    @Column(name = "id_exercise")
    private Long id_exercise;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private ExerciseType type;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutsExercises> workoutExercises;

    public Exercises(String name, ExerciseType type) {
        this.name = name;
        this.type = type;
    }
}
