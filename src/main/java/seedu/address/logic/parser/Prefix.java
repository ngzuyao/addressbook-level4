package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof seedu.address.logic.parser.Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        seedu.address.logic.parser.Prefix otherPrefix = (seedu.address.logic.parser.Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }
}
