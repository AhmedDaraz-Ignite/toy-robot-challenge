package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.types.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToyRobot {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Position position;
}
