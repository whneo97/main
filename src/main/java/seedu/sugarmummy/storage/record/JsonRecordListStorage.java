package seedu.sugarmummy.storage.record;

import java.nio.file.Path;

import seedu.sugarmummy.model.record.UniqueRecordList;
import seedu.sugarmummy.storage.JsonGeneralStorage;

/**
 * Represents the specific version {@code JsonGeneralStorage} about record list.
 */
public class JsonRecordListStorage extends JsonGeneralStorage<UniqueRecordList, JsonSerializableRecordList> {

    public JsonRecordListStorage(Path filePath) {
        super(filePath, UniqueRecordList.class, JsonSerializableRecordList.class);
    }
}
