package com.example.agenda_fit_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlredyExistException extends AgendaFitException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pd.setTitle("User Already Exist");
        pd.setDetail("This mail or login already exists");

        return pd;
    }
}
