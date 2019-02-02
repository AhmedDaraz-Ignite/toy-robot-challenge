package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.types.Direction;
import com.challenge.puzzle.toyrobot.types.Position;
import com.challenge.puzzle.toyrobot.types.ToyRobotDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link ToyRobotMapperUtility}.
 */
public class ToyRobotMapperUtilityTest {


    private static final long TOY_ROBOT_ID = 1L;
    private static final int ROW = 1;
    private static final int COLUMN = 3;
    private static final Direction DIRECTION = Direction.EAST;

    @Test
    public void mapToToyRobotDtoWillReturnMappedDto() {
        ToyRobot toyRobot = buildToyRobotEntity();
        ToyRobotDto toyRobotDto = ToyRobotMapperUtility.mapToToyRobotDto(toyRobot);
        assertThat(toyRobotDto.getId()).isEqualTo(toyRobot.getId());
        assertThat(toyRobotDto.getX()).isEqualTo(toyRobot.getPosition().getRow());
        assertThat(toyRobotDto.getY()).isEqualTo(toyRobot.getPosition().getColumn());
        assertThat(toyRobotDto.getF()).isEqualTo(toyRobot.getPosition().getDirection());
    }


    @Test
    public void mapFromToyRobotDtoWillReturnMappedEntity() {
        ToyRobotDto toyRobotDto = buildToyRobotDto();
        ToyRobot toyRobot = ToyRobotMapperUtility.mapFromToyRobotDto(toyRobotDto);
        assertThat(toyRobot.getId()).isEqualTo(toyRobotDto.getId());
        assertThat(toyRobot.getPosition().getRow()).isEqualTo(toyRobotDto.getX());
        assertThat(toyRobot.getPosition().getColumn()).isEqualTo(toyRobotDto.getY());
        assertThat(toyRobot.getPosition().getDirection()).isEqualTo(toyRobotDto.getF());
    }

    private ToyRobotDto buildToyRobotDto() {
        return new ToyRobotDto(TOY_ROBOT_ID, ROW, COLUMN, DIRECTION);
    }

    private ToyRobot buildToyRobotEntity() {
        return new ToyRobot(TOY_ROBOT_ID, new Position(ROW, COLUMN, DIRECTION));
    }
}