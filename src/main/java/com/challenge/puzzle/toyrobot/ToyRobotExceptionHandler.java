package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.exceptions.InvalidToyRobotException;
import com.challenge.puzzle.toyrobot.exceptions.ToyRobotNotFoundException;
import com.challenge.puzzle.toyrobot.types.ToyRobotApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ToyRobotExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ToyRobotApiError> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(new ToyRobotApiError(INTERNAL_SERVER_ERROR, ex.getMessage()));
    }


    @ExceptionHandler(ToyRobotNotFoundException.class)
    public ResponseEntity<ToyRobotApiError> handleException(ToyRobotNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(new ToyRobotApiError(NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(InvalidToyRobotException.class)
    public ResponseEntity<ToyRobotApiError> handleException(InvalidToyRobotException ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(new ToyRobotApiError(BAD_REQUEST, ex.getMessage()));
    }

    private ResponseEntity<ToyRobotApiError> buildResponseEntity(ToyRobotApiError error) {
        return new ResponseEntity<ToyRobotApiError>(error, error.getStatus());
    }
}
