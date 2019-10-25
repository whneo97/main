package seedu.sugarmummy.model.record;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a record book
 */
public interface ReadOnlyRecordBook extends ReadOnlyData {

    /**
     * Returns an unmodifiable view of the records list. This list will not contain any duplicate records.
     */
    ObservableList<Record> getRecordList();

}
