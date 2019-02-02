package com.challenge.puzzle.toyrobot.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Position {
    private int row;
    private int column;
    private Direction direction;
}
