package com.example.agenda_fit_v2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate date;

    @Column(name = "hours")
    private double hours;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnoreProperties("workout")
    private List<WorkoutsExercises> workoutExercises = new ArrayList<>();

    @Column(name = "type")
    private int type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Workouts() {

    }

    public Workouts(String title, String description, LocalDate date, double hours, List<WorkoutsExercises> workoutExercises, int type, Users user) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.hours = hours;
        this.workoutExercises = workoutExercises;
        this.type = type;
        this.user = user;
    }
}
