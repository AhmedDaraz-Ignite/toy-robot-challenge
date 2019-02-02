package com.challenge.puzzle.toyrobot.types;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ToyRobotApiError {

    private HttpStatus status;

    /*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")*/
    private LocalDateTime timestamp;

    private String message;

    public ToyRobotApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
