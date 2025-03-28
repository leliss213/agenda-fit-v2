package com.example.agenda_fit_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import java.util.UUID;

public class WorkoutDoesNotExistException extends AgendaFitException{

    private final UUID workoutId;

    public WorkoutDoesNotExistException(UUID workoutId) {
        this.workoutId = workoutId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Workout Not Found");
        pd.setDetail("This id workout does not exist, " + workoutId);
        return pd;
    }
}
