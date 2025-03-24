package com.example.agenda_fit_v2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workouts")
@Getter
@Setter
public class Workouts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_workout")
    private UUID id_workout;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Column(name = "hours")
    private double hours;

    @OneToMany(mappedBy = "workout")
    private List<WorkoutsExercises> workoutExercises;

    @Column(name = "type")
    private int tipo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Workouts() {

    }
}
