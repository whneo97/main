package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.sugarmummy.model.food.TypicalFoods.FOODS;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.sugarmummy.commons.core.GuiSettings;
import seedu.sugarmummy.model.ModelManager;
import seedu.sugarmummy.model.UserPrefs;
import seedu.sugarmummy.model.bio.UserList;
import seedu.sugarmummy.model.calendar.Calendar;
import seedu.sugarmummy.model.food.UniqueFoodList;
import seedu.sugarmummy.model.record.UniqueRecordList;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        UniqueFoodList foodList = new UniqueFoodList();
        foodList.setFoods(FOODS);
        UniqueRecordList recordList = new UniqueRecordList();
        UserList userList = new UserList();
        Calendar calendar = new Calendar();

        // same values -> returns true
        modelManager = new ModelManager(userPrefs, userList, foodList, recordList, calendar);
        ModelManager modelManagerCopy = new ModelManager(userPrefs, userList, foodList, recordList,
                calendar);

        // same values -> returns true
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        //        // different addressBook -> returns false
        //        assertFalse(modelManager.equals(new ModelManager(userPrefs, userList, foodList,
        //                recordList, calendar)));

        // different filteredList -> returns false
        //        String[] keywords = ALICE.getName().fullName.split("\\s+");
        //        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        //        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs, userList, foodList,
        //        recordList,
        //                calendar)));
        //
        //        // resets modelManager to initial state for upcoming tests
        //        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(differentUserPrefs, userList, foodList,
                recordList, calendar)));
    }
}
