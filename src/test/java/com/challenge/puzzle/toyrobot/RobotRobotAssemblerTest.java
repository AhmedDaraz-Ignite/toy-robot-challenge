package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.types.Direction;
import com.challenge.puzzle.toyrobot.types.ToyRobotDto;
import org.junit.Test;
import org.springframework.hateoas.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * Test for {@link ToyRobotAssembler}.
 */
public class RobotRobotAssemblerTest {

    private static final long TOY_ROBOT_ID = 1L;
    private static final int ROW = 1;
    private static final int COLUMN = 3;
    private static final Direction DIRECTION = Direction.EAST;
    private ToyRobotAssembler toyRobotAssembler = new ToyRobotAssembler();

    @Test
    public void toResourceWillReturnHateoasResource() {

        String selfLink = "toys/" + TOY_ROBOT_ID;
        String leftLink = "toys/" + TOY_ROBOT_ID + "/left";
        String rightLink = "toys/" + TOY_ROBOT_ID + "/right";
        String moveLink = "toys/" + TOY_ROBOT_ID + "/move";

        List<String> links = Arrays.asList(selfLink, leftLink, rightLink, moveLink);
        List<String> linkNames = Arrays.asList("self", "left", "right", "move");

        Resource<ToyRobotDto> toyRobotDtoResource = toyRobotAssembler.toResource(buildToyRobotDto());

        toyRobotDtoResource
                .getLinks()
                .stream()
                .allMatch(link -> links.contains(link.getHref()) && linkNames.contains(link.getRel()));
    }

    private ToyRobotDto buildToyRobotDto() {
        return new ToyRobotDto(TOY_ROBOT_ID, ROW, COLUMN, DIRECTION);
    }

}