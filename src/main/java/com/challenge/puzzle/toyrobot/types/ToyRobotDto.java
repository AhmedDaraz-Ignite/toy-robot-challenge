package com.challenge.puzzle.toyrobot.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToyRobotDto {
    private long id;
    private int x;
    private int y;
    private Direction f;
}
