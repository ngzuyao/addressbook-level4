package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NICKNAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nickname;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * Adds a nickname to person in the list.
 */
public class NicknameCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "nickname";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the person's nickname identified by the index number used in the last person listing.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + PREFIX_NICKNAME + "NICKNAME "
            + "Example: " + COMMAND_WORD + " 1" + " nn/" + "bob";

    public static final String MESSAGE_UPDATE_PERSON_NICKNAME_SUCCESS = "Updated Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index targetIndex;

    private final Nickname nickname;

    /**
     * Command for creating a nickname field for a person in the addressbook.
     * @param targetIndex
     * @param nickname
     */
    public NicknameCommand(Index targetIndex, Nickname nickname) {
        this.targetIndex = targetIndex;
        this.nickname = nickname;
    }

    /**
     * updates the person's nickname.
     * @param personToUpdateNickname
     * @param nickname
     * @return
     */
    private Person updatePersonNickname(ReadOnlyPerson personToUpdateNickname, Nickname nickname) {
        Name name = personToUpdateNickname.getName();
        Phone phone = personToUpdateNickname.getPhone();
        Email email = personToUpdateNickname.getEmail();
        Address address = personToUpdateNickname.getAddress();
        Set<Tag> tags = personToUpdateNickname.getTags();

        Person personUpdated = new Person(name, phone, email, address, nickname, tags);

        return personUpdated;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToUpdateNickname = lastShownList.get(targetIndex.getZeroBased());
        Person personUpdated = updatePersonNickname(personToUpdateNickname, nickname);

        try {
            model.updatePerson(personToUpdateNickname, personUpdated);
        } catch (DuplicatePersonException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } catch (PersonNotFoundException pnfe) {
            throw new AssertionError("The target person cannot be missing");
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_UPDATE_PERSON_NICKNAME_SUCCESS, personUpdated));
    }
}
