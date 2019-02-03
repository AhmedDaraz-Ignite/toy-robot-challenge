package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.exceptions.ToyRobotException;
import com.google.common.base.Throwables;
import com.challenge.puzzle.toyrobot.types.ToyRobotDto;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ToyRobotAssembler implements ResourceAssembler<ToyRobotDto, Resource<ToyRobotDto>> {

    @Override
    public Resource<ToyRobotDto> toResource(ToyRobotDto robot) {
        Resource<ToyRobotDto> toyRobotDtoResource = null;
        try {
            toyRobotDtoResource = new Resource<>(robot,
                    linkTo(methodOn(ToyRobotController.class).report(robot.getId())).withSelfRel(),
                    linkTo(methodOn(ToyRobotController.class).left(robot.getId())).withRel("left"),
                    linkTo(methodOn(ToyRobotController.class).right(robot.getId())).withRel("right"),
                    linkTo(methodOn(ToyRobotController.class).move(robot.getId())).withRel("move"));
        } catch (ToyRobotException e) {
            Throwables.propagate(e);
        }
        return toyRobotDtoResource;
    }
}
