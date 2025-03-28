package com.example.agenda_fit_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import java.util.UUID;

public class UserDoesNotExistException extends AgendaFitException {

    private final UUID userId;

    public UserDoesNotExistException(UUID userId) {
        this.userId = userId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setDetail("User " + userId + " does not exist");
        pd.setTitle("User does not found");
        return pd;

    }
}
