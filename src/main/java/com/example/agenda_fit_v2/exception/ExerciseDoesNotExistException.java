package com.example.agenda_fit_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ExerciseDoesNotExistException extends AgendaFitException {

  @Override
  public ProblemDetail toProblemDetail() {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pd.setTitle("Exercise does not exist");
    pd.setDetail("Exercise does not exist");
    return pd;
  }
}
