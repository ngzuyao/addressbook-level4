package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's nickname in the address book.
 * Guarantees: immutable.
 */
public class Nickname {

    public static final String MESSAGE_NICKNAME_CONSTRAINTS =
            "Person nickname should be in dd/mm/yyyy format, and it should not be blank";

    /*
     * The first character of the nickname must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public final String value;

    /**
     * Validates given nickname.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Nickname(String nicknameNum) throws IllegalValueException {
        requireNonNull(nicknameNum);
        this.value = nicknameNum;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.Nickname // instanceof handles nulls
                && this.value.equals(((seedu.address.model.person.Nickname) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
