package com.example.agenda_fit_v2.entity;

import lombok.Getter;

@Getter
public enum ExerciseType {

    PEITO(1), OMBRO(2), BICEPS(3), TRICEPS(4), COSTAS(5), PERNAS(6);

    private final int value;

    ExerciseType(int value) {
        this.value = value;
    }

    public static ExerciseType fromValue(int value) {
        for (ExerciseType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ExerciseType value: " + value);
    }
}
