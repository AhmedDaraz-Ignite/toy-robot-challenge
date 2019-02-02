package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.exceptions.ToyRobotException;
import com.challenge.puzzle.toyrobot.exceptions.ToyRobotNotFoundException;
import com.challenge.puzzle.toyrobot.types.Direction;
import com.challenge.puzzle.toyrobot.types.Position;
import com.challenge.puzzle.toyrobot.types.ToyRobotDto;
import com.google.common.base.Throwables;
import lombok.val;
import lombok.var;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import static com.challenge.puzzle.toyrobot.ToyRobotMapperUtility.mapFromToyRobotDto;

@Service
class ToyRobotService {

    private static final String ROBOTS_CACHE = "robots";
    private ToyRobotRepository repository;
    private ToyRobotValidationService validator;

    public ToyRobotService(ToyRobotRepository repository, ToyRobotValidationService validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public ToyRobotDto placeToyRobot(ToyRobotDto toy) {

        var toyRobot = mapFromToyRobotDto(toy);

        try {
            validator.validatePosition(toyRobot.getPosition());
        } catch (ToyRobotException e) {
            Throwables.propagate(e);
        }

        toyRobot = repository.save(toyRobot);

        toy.setId(toyRobot.getId());
        return toy;
    }


    @CachePut(ROBOTS_CACHE)
    public ToyRobotDto reportToyRobot(long id) throws ToyRobotException {

        return repository.findById(id)
                .map(ToyRobotMapperUtility::mapToToyRobotDto)
                .orElseThrow(() -> new ToyRobotNotFoundException(MessageFormat.format("ToyRobot with ID ({0}) is not existing", id)));
    }

    public void moveToyRobot(long id) throws ToyRobotException {
        var toy = repository.findById(id)
                .orElseThrow(() -> new ToyRobotNotFoundException(MessageFormat.format("ToyRobot with ID ({0}) is not existing", id)));

        val column = toy.getPosition().getColumn();
        val row = toy.getPosition().getRow();
        val direction = toy.getPosition().getDirection();
        val newPosition = whatIsNextPosition(toy, column, row, direction);

        validator.validatePosition(newPosition);
        toy.setPosition(newPosition);

        repository.save(toy);
    }

    private Position whatIsNextPosition(ToyRobot toy, int column, int row, Direction direction) {
        switch (direction) {
            case NORTH:
                return new Position(row + 1, column, Direction.NORTH);
            case SOUTH:
                return new Position(row - 1, column, Direction.SOUTH);
            case EAST:
                return new Position(row, column + 1, Direction.EAST);
            case WEST:
                return new Position(row, column - 1, Direction.WEST);
            default:
                // Same old position
                return new Position(row, column, direction);
        }
    }

    public void rightRotateToyRobot(long id) throws ToyRobotException {

        var toy = repository.findById(id)
                .orElseThrow(() -> new ToyRobotNotFoundException(MessageFormat.format("ToyRobot with ID ({0}) is not existing", id)));

        val direction = toy.getPosition().getDirection();

        switch (direction) {
            case NORTH:
                toy.getPosition().setDirection(Direction.EAST);
                break;
            case SOUTH:
                toy.getPosition().setDirection(Direction.WEST);
                break;
            case EAST:
                toy.getPosition().setDirection(Direction.SOUTH);
                break;
            case WEST:
                toy.getPosition().setDirection(Direction.NORTH);
                break;
        }

        repository.save(toy);
    }

    public void leftRotateToyRobot(long id) throws ToyRobotException {
        var toy = repository.findById(id)
                .orElseThrow(() -> new ToyRobotNotFoundException(MessageFormat.format("ToyRobot with ID ({0}) is not existing", id)));

        val direction = toy.getPosition().getDirection();
        switch (direction) {
            case NORTH:
                toy.getPosition().setDirection(Direction.WEST);
                break;
            case SOUTH:
                toy.getPosition().setDirection(Direction.EAST);
                break;
            case EAST:
                toy.getPosition().setDirection(Direction.NORTH);
                break;
            case WEST:
                toy.getPosition().setDirection(Direction.SOUTH);
                break;
        }

        repository.save(toy);
    }

}
