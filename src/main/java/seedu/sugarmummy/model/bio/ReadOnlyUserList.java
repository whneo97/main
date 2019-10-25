package seedu.sugarmummy.model.bio;

import javafx.collections.ObservableList;
import seedu.sugarmummy.model.record.ReadOnlyData;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyUserList extends ReadOnlyData {

    /**
     * Returns an unmodifiable view of the user's bio data. This list will not contain any duplicate users.
     */
    ObservableList<User> getUserList();
}
