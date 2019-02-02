package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.exceptions.InvalidToyRobotException;
import com.challenge.puzzle.toyrobot.types.Position;
import lombok.val;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ToyRobotValidationService {

    private static final int MAX_COLUMN_LENGTH = 5;
    private static final int MAX_ROW_LENGTH = 5;
    private static final int MIN_ROW_LENGTH = 0;
    private static final int MIN_COLUMN_LENGTH = 0;


    public void validatePosition(Position position) throws InvalidToyRobotException {
        // validate position
        val row = position.getRow();
        val column = position.getColumn();
        if (!isValidRow(row) || !isValidColumn(column)) {
            throw new InvalidToyRobotException(MessageFormat.format("Invalid ToyRobot Position ({0}),  ({1})", row, column));
        }
    }

    private boolean isValidColumn(int column) {
        return column >= MIN_COLUMN_LENGTH && column < MAX_COLUMN_LENGTH;
    }

    private boolean isValidRow(int row) {
        return row >= MIN_ROW_LENGTH && row < MAX_ROW_LENGTH;
    }

}
