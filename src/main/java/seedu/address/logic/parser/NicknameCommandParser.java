package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NICKNAME;

import java.util.StringTokenizer;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.NicknameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nickname;

/**
 * Parses input arguments and creates a new NicknameCommand object
 */
public class NicknameCommandParser implements Parser<NicknameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NicknameCommand
     * and returns a NicknameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NicknameCommand parse(String args) throws ParseException {
        try {
            StringTokenizer st = new StringTokenizer(args);
            Index index = ParserUtil.parseIndex(st.nextToken());
            String nicknameInput = st.nextToken();
            String prefix = nicknameInput.substring(0, 3);

            if (!prefix.equals(PREFIX_NICKNAME.getPrefix())) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, NicknameCommand.MESSAGE_USAGE));
            }

            Nickname nickname = new Nickname(nicknameInput.substring(3));
            return new NicknameCommand(index, nickname);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NicknameCommand.MESSAGE_USAGE));
        }
    }

}
