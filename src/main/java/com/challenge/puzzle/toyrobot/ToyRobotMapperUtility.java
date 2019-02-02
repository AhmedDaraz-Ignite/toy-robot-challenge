package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.types.Direction;
import com.challenge.puzzle.toyrobot.types.ToyRobotDto;
import lombok.experimental.UtilityClass;
import lombok.var;
import org.modelmapper.ModelMapper;

@UtilityClass
public class ToyRobotMapperUtility {

    public ToyRobotDto mapToToyRobotDto(ToyRobot toyRobot) {
        var modelMapper = new ModelMapper();

        var typeMap = modelMapper.createTypeMap(ToyRobot.class, ToyRobotDto.class);

        typeMap.addMappings(mapper ->
                mapper.<Integer>map(src -> src.getPosition().getRow(), (dest, v) -> dest.setX(v)));

        typeMap.addMappings(mapper ->
                mapper.<Integer>map(src -> src.getPosition().getColumn(), (dest, v) -> dest.setY(v)));

        typeMap.addMappings(mapper ->
                mapper.<Direction>map(src -> src.getPosition().getDirection(), (dest, v) -> dest.setF(v)));

        return modelMapper.map(toyRobot, ToyRobotDto.class);
    }

    public ToyRobot mapFromToyRobotDto(ToyRobotDto toyRobot) {
        var modelMapper = new ModelMapper();

        var typeMap = modelMapper.createTypeMap(ToyRobotDto.class, ToyRobot.class);

        typeMap.addMappings(mapper ->
                mapper.<Integer>map(src -> src.getX(), (dest, v) -> dest.getPosition().setRow(v)));

        typeMap.addMappings(mapper ->
                mapper.<Integer>map(src -> src.getY(), (dest, v) -> dest.getPosition().setColumn(v)));

        typeMap.addMappings(mapper ->
                mapper.<Direction>map(src -> src.getF(), (dest, v) -> dest.getPosition().setDirection(v)));

        return modelMapper.map(toyRobot, ToyRobot.class);
    }
}
