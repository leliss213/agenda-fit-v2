package com.example.agenda_fit_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AgendaFitException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Agendafit internal server error");

        return pd;
    }
}
