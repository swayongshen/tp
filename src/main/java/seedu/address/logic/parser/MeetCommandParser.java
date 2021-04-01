package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.MeetCommand.ADD_MEETING;
import static seedu.address.logic.commands.MeetCommand.CLEAR_MEETING;
import static seedu.address.logic.commands.MeetCommand.DELETE_MEETING;
import static seedu.address.logic.commands.MeetCommand.MEETING_EMPTY;
import static seedu.address.logic.commands.MeetCommand.MESSAGE_USAGE;
import static seedu.address.model.meeting.Meeting.isValidMeeting;

import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MeetCommandParser implements Parser<MeetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MeetCommand
     * and returns a MeetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MeetCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        try {
            String[] splitArgs = trimmedArgs.split("\\s+", 5);

            if (!splitArgs[1].equals(ADD_MEETING) && !splitArgs[1].equals(DELETE_MEETING)
                    && !splitArgs[1].equals(CLEAR_MEETING)) {
                if (isValidMeeting(splitArgs[1], splitArgs[2], splitArgs[3], splitArgs[4])) {
                    return new MeetCommand(ParserUtil.parseIndex(splitArgs[0]), ADD_MEETING,
                            splitArgs[1], splitArgs[2], splitArgs[3], splitArgs[4]);
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
                }
            }

            splitArgs = trimmedArgs.split("\\s+", 6);
            if (splitArgs[1].equals(MeetCommand.CLEAR_MEETING)) {
                return new MeetCommand(ParserUtil.parseIndex(splitArgs[0]), CLEAR_MEETING,
                        MEETING_EMPTY, MEETING_EMPTY, MEETING_EMPTY, MEETING_EMPTY);
            }

            if (!isValidMeeting(splitArgs[2], splitArgs[3], splitArgs[4], splitArgs[5])) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            }

            return new MeetCommand(ParserUtil.parseIndex(splitArgs[0]), splitArgs[1],
                    splitArgs[2], splitArgs[3], splitArgs[4], splitArgs[5]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
