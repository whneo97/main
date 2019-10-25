package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.sugarmummy.commons.core.GuiSettings;
import seedu.sugarmummy.logic.commands.records.AddCommand;
import seedu.sugarmummy.model.Model;
import seedu.sugarmummy.model.ReadOnlyUserPrefs;
import seedu.sugarmummy.model.aesthetics.Background;
import seedu.sugarmummy.model.aesthetics.Colour;
import seedu.sugarmummy.model.bio.ReadOnlyUserList;
import seedu.sugarmummy.model.bio.User;
import seedu.sugarmummy.model.calendar.CalendarEntry;
import seedu.sugarmummy.model.calendar.DateTime;
import seedu.sugarmummy.model.calendar.ReadOnlyCalendar;
import seedu.sugarmummy.model.calendar.Reminder;
import seedu.sugarmummy.model.food.Food;
import seedu.sugarmummy.model.food.UniqueFoodList;
import seedu.sugarmummy.model.record.BloodSugar;
import seedu.sugarmummy.model.record.Bmi;
import seedu.sugarmummy.model.record.Concentration;
import seedu.sugarmummy.model.record.Height;
import seedu.sugarmummy.model.record.Record;
import seedu.sugarmummy.model.record.RecordType;
import seedu.sugarmummy.model.record.UniqueRecordList;
import seedu.sugarmummy.model.record.Weight;
import seedu.sugarmummy.model.statistics.AverageType;

public class AddCommandTest {

    private LocalDate ld = LocalDate.of(1970, Month.JANUARY, 1);
    private LocalTime lt = LocalTime.of(8, 0, 0);
    private DateTime dt = new DateTime(ld, lt);
    private BloodSugar bs = new BloodSugar(new Concentration("12.34"), dt);
    private Bmi bmi = new Bmi(new Height("12.34"), new Weight("23.34"), dt);

    @Test
    public void constructor_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    //    @Test
    //    public void execute_recordAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingRecordAdded modelStub = new ModelStubAcceptingRecordAdded();
    //
    //        //        Person validPerson = new PersonBuilder().build();
    //        CommandResult commandResult = new AddCommand(bs).execute(modelStub);
    //        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, bs), commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(bs), modelStub.recordsAdded);
    //    }
    //
    //    @Test
    //    public void execute_duplicateRecord_throwsCommandException() {
    //        //        Person validPerson = new PersonBuilder().build();
    //
    //        AddCommand addCommand = new AddCommand(bs);
    //        ModelStub modelStub = new ModelStubWithRecord(bs);
    //
    //        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_RECORD, () ->
    //        addCommand.execute(modelStub));
    //    }

    @Test
    public void equals() {
        AddCommand addBloodSugarCommand = new AddCommand(bs);
        AddCommand addBmiCommand = new AddCommand(bmi);

        // same object -> returns true
        assertTrue(addBloodSugarCommand.equals(addBloodSugarCommand));

        // same values -> returns true
        AddCommand addBloodSugarCommandCopy = new AddCommand(bs);
        assertTrue(addBloodSugarCommand.equals(addBloodSugarCommandCopy));

        // different types -> returns false
        assertFalse(addBloodSugarCommand.equals(1));

        // null -> returns false
        assertFalse(addBloodSugarCommand.equals(null));

        // different record -> returns false
        assertFalse(addBloodSugarCommand.equals(addBmiCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFood(Food food) {
            return false;
        }

        @Override
        public void addFood(Food food) {
        }

        @Override
        public UniqueFoodList getUniqueFoodListObject() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getFoodList() {
            return null;
        }

        @Override
        public void setFoodList(UniqueFoodList newFoodList) {
        }

        @Override
        public void addRecord(Record toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UniqueRecordList getUniqueRecordListObject() {
            return null;
        }

        @Override
        public ObservableList<Record> getRecordList() {
            return null;
        }

        @Override
        public void setRecordList(UniqueRecordList newRecordList) {

        }

        @Override
        public ObservableList<Record> getFilterRecordList() {
            return null;
        }

        @Override
        public boolean hasRecord(Record toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecord(Record record) {

        }

        @Override
        public void updateFilteredRecordList(Predicate<Record> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getFilterFoodList() {
            return null;
        }

        @Override
        public void updateFilteredFoodList(Predicate<Food> predicate) {

        }

        @Override
        public boolean bioExists() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserList getUserList() {
            throw new AssertionError("This method should not be called.");
        }

        //=========== User List =============================================================
        @Override
        public void setUserList(ReadOnlyUserList userList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getUserListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserListFilePath(Path userListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addUser(User user) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public ObservableList<User> getFilteredUserList() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void updateFilteredUserList(Predicate<User> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCalendarEntry(CalendarEntry calendarEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCalendarEntry(CalendarEntry target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCalendarEntry(CalendarEntry calendarEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPastReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCalendarEntry(CalendarEntry target, CalendarEntry editedCalendarEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<CalendarEntry> getFilteredCalendarEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<CalendarEntry> getPastReminderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void schedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void stopAllReminders() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUser(User target, User editedUser) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== Statistics List =============================================================

        @Override
        public SimpleStringProperty getAverageType() {
            throw new AssertionError("This method should not be called.");
        }

        //=========== Aesthetics =============================================================

        @Override
        public Colour getFontColour() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFontColour(Colour fontColour) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Background getBackground() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBackground(Background background) {
            throw new AssertionError("This method should not be called.");
        }


        //=========== Records =============================================================

        @Override
        public SimpleStringProperty getRecordType() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void calculateAverageMap(AverageType averageType, RecordType recordType, int count) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableMap<LocalDate, Double> getAverageMap() {
            throw new AssertionError("This method should not be called.");
        }

        //    /**
        //     * A Model stub that contains a single person.
        //     */
        //    private class ModelStubWithPerson extends ModelStub {
        //        private final Person person;
        //
        //        ModelStubWithPerson(Person person) {
        //            requireNonNull(person);
        //            this.person = person;
        //        }
        //
        //        @Override
        //        public boolean hasPerson(Person person) {
        //            requireNonNull(person);
        //            return this.person.isSamePerson(person);
        //        }
        //    }

        //     * A Model stub that always accept the person being added.
        //     */
        //    private class ModelStubAcceptingPersonAdded extends ModelStub {
        //        final ArrayList<Person> personsAdded = new ArrayList<>();
        //
        //        @Override
        //        public boolean hasPerson(Person person) {
        //            requireNonNull(person);
        //            return personsAdded.stream().anyMatch(person::isSamePerson);
        //        }
        //
        //        @Override
        //        public void addPerson(Person person) {
        //            requireNonNull(person);
        //            personsAdded.add(person);
        //        }
        //
        //        @Override
        //        public ReadOnlyAddressBook getAddressBook() {
        //            return new AddressBook();
        //        }

        //    /**
        //     * A Model stub that always accept the person being added.
        //     */
        //    private class ModelStubAcceptingRecordAdded extends ModelStub {
        //        final ArrayList<Record> recordsAdded = new ArrayList<>();
        //
        //        @Override
        //        public boolean hasRecord(Record record) {
        //            requireNonNull(record);
        //            return recordsAdded.stream().anyMatch(record::isSameRecord);
        //        }
        //
        //        @Override
        //        public void addRecord(Record record) {
        //            requireNonNull(record);
        //            recordsAdded.add(record);
        //        }
        //    }

        //     * A Model stub that contains a single person.
        //     */
        //    private class ModelStubWithRecord extends ModelStub {
        //        private final Record record;
        //
        //        ModelStubWithRecord(Record record) {
        //            requireNonNull(record);
        //            this.record = record;
        //        }
        //
        //        @Override
        //        public boolean hasRecord(Record record) {
        //            requireNonNull(record);
        //            return this.record.isSameRecord(record);
        //        }
        //    }
    }

}
