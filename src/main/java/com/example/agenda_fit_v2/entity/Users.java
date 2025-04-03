package com.example.agenda_fit_v2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user")
    private UUID iduser;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birth_date;

    @Column(name = "height")
    private double height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "token")
    private String token;

    @OneToMany(mappedBy = "user")
    private List<Workouts> workouts;

    public Users() {
    }

    public Users(String login,
                 String password,
                 String email,
                 LocalDate birth_date,
                 double height,
                 double weight,
                 String token) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.birth_date = birth_date;
        this.height = height;
        this.weight = weight;
        this.token = token;
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
