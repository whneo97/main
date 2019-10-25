package seedu.sugarmummy.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import seedu.sugarmummy.commons.core.GuiSettings;
import seedu.sugarmummy.commons.core.LogsCenter;
import seedu.sugarmummy.commons.util.CollectionUtil;
import seedu.sugarmummy.model.aesthetics.Background;
import seedu.sugarmummy.model.aesthetics.Colour;
import seedu.sugarmummy.model.bio.ReadOnlyUserList;
import seedu.sugarmummy.model.bio.User;
import seedu.sugarmummy.model.bio.UserList;
import seedu.sugarmummy.model.calendar.Calendar;
import seedu.sugarmummy.model.calendar.CalendarEntry;
import seedu.sugarmummy.model.calendar.ReadOnlyCalendar;
import seedu.sugarmummy.model.calendar.Reminder;
import seedu.sugarmummy.model.food.Food;
import seedu.sugarmummy.model.food.UniqueFoodList;
import seedu.sugarmummy.model.record.Record;
import seedu.sugarmummy.model.record.RecordType;
import seedu.sugarmummy.model.record.UniqueRecordList;
import seedu.sugarmummy.model.statistics.AverageMap;
import seedu.sugarmummy.model.statistics.AverageType;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final FilteredList<User> filteredUserList;
    private final UniqueFoodList foodList;
    private final UserList userList;
    private final FilteredList<Food> filteredFoodList;
    private final UniqueRecordList recordList;
    private final FilteredList<Record> filteredRecordList;
    private final Calendar calendar;
    private final FilteredList<CalendarEntry> filteredCalenderEntryList;
    private final FilteredList<CalendarEntry> pastReminderList;
    private final AverageMap averageMap;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyUserPrefs userPrefs, ReadOnlyUserList userList,
                        UniqueFoodList foodList, UniqueRecordList recordList,
                        ReadOnlyCalendar calendar) {
        super();
        CollectionUtil.requireAllNonNull(userPrefs, foodList, userList, recordList, calendar);

        logger.fine("Initializing with bio: " + userList + " and user prefs " + userPrefs
                + " and food map: " + foodList + " and record list: " + recordList + " and calendar: " + calendar);

        this.userPrefs = new UserPrefs(userPrefs);
        this.userList = new UserList(userList);
        this.filteredUserList = new FilteredList<>(this.userList.getUserList());
        this.foodList = foodList;
        this.filteredFoodList = new FilteredList<>(this.foodList.asUnmodifiableObservableList());
        this.recordList = recordList;
        this.filteredRecordList = new FilteredList<>(this.recordList.asUnmodifiableObservableList());
        this.calendar = new Calendar(calendar);
        this.filteredCalenderEntryList = new FilteredList<>(this.calendar.getCalendarEntryList());
        this.pastReminderList = new FilteredList<>(this.calendar.getPastReminderList());
        this.averageMap = new AverageMap();
    }

    public ModelManager() {
        this(new UserPrefs(), new UserList(), new UniqueFoodList(), new UniqueRecordList(),
                new Calendar());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    //=========== Filtered List Accessors =============================================================

    @Override
    public boolean equals(
            Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return userList.equals(other.userList)
                && foodList.equals(other.foodList)
                && calendar.equals(other.calendar)
                && recordList.equals(other.recordList)
                && pastReminderList.equals(other.pastReminderList)
                && userPrefs.equals(other.userPrefs)
                && filteredUserList.equals(other.filteredUserList)
                && filteredFoodList.equals(other.filteredFoodList)
                && filteredCalenderEntryList.equals(other.filteredCalenderEntryList)
                && filteredRecordList.equals(other.filteredRecordList);

    }

    //=========== User List =============================================================

    @Override
    public boolean bioExists() {
        return !this.userList.isEmpty();
    }

    @Override
    public ReadOnlyUserList getUserList() {
        return userList;
    }

    @Override
    public void setUserList(ReadOnlyUserList userList) {
        this.userList.resetData(userList);
    }

    @Override
    public Path getUserListFilePath() {
        return userPrefs.getUserListFilePath();
    }

    @Override
    public void setUserListFilePath(Path userListFilePath) {
        requireNonNull(userListFilePath);
        userPrefs.setUserListFilePath(userListFilePath);
    }

    @Override
    public boolean hasUser(User user) {
        requireNonNull(user);
        return userList.hasUser(user);
    }

    @Override
    public void addUser(User user) {
        userList.addUser(user);
        updateFilteredUserList(PREDICATE_SHOW_ALL_USERS);
    }

    /**
     * Returns an unmodifiable view of the list of {@code User} backed by the internal list of {@code
     * versionedAddressBook}
     */
    @Override
    public ObservableList<User> getFilteredUserList() {
        return filteredUserList;
    }

    @Override
    public void updateFilteredUserList(Predicate<User> predicate) {
        requireNonNull(predicate);
        filteredUserList.setPredicate(predicate);
    }

    //Calendar
    @Override
    public ReadOnlyCalendar getCalendar() {
        return calendar;
    }

    @Override
    public boolean hasCalendarEntry(CalendarEntry calendarEntry) {
        requireNonNull(calendarEntry);
        return calendar.hasCalendarEntry(calendarEntry);
    }

    @Override
    public void deleteCalendarEntry(CalendarEntry target) {
        calendar.removeCalendarEntry(target);
    }

    @Override
    public void addCalendarEntry(CalendarEntry calendarEntry) {
        calendar.addCalendarEntry(calendarEntry);
    }

    @Override
    public void addPastReminder(Reminder reminder) {
        calendar.addPastReminder(reminder);
    }

    @Override
    public void setCalendarEntry(CalendarEntry target, CalendarEntry editedCalendarEntry) {
        CollectionUtil.requireAllNonNull(target, editedCalendarEntry);
        calendar.setCalendarEntry(target, editedCalendarEntry);
    }

    @Override
    public ObservableList<CalendarEntry> getFilteredCalendarEntryList() {
        return filteredCalenderEntryList;
    }

    @Override
    public ObservableList<CalendarEntry> getPastReminderList() {
        return pastReminderList;
    }

    @Override
    public void schedule() {
        calendar.schedule();
    }

    @Override
    public void stopAllReminders() {
        calendar.stopAllReminders();
    }

    @Override
    public void setUser(User target, User editedUser) {
        CollectionUtil.requireAllNonNull(target, editedUser);
        userList.setUser(target, editedUser);
    }


    //=========== Aesthetics =============================================================

    @Override
    public Colour getFontColour() {
        return userPrefs.getFontColour();
    }

    @Override
    public void setFontColour(Colour fontColour) {
        userPrefs.setFontColour(fontColour);
    }

    @Override
    public Background getBackground() {
        return userPrefs.getBackground();
    }

    @Override
    public void setBackground(Background background) {
        userPrefs.setBackground(background);
    }

    //=========== Food Map =============================================================

    @Override
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return foodList.contains(food);
    }

    @Override
    public void addFood(Food food) {
        foodList.add(food);
    }

    @Override
    public UniqueFoodList getUniqueFoodListObject() {
        return foodList;
    }

    @Override
    public ObservableList<Food> getFoodList() {
        return foodList.asUnmodifiableObservableList();
    }

    @Override
    public void setFoodList(UniqueFoodList uniqueFoodLists) {
        CollectionUtil.requireAllNonNull(uniqueFoodLists);
        foodList.setFoods(uniqueFoodLists);
    }

    @Override
    public ObservableList<Food> getFilterFoodList() {
        return filteredFoodList;
    }

    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        filteredFoodList.setPredicate(predicate);
    }

    //=========== Records =============================================================
    @Override
    public boolean hasRecord(Record record) {
        requireNonNull(record);
        return recordList.contains(record);
    }

    @Override
    public void deleteRecord(Record record) {
        recordList.remove(record);
    }

    @Override
    public void addRecord(Record record) {
        recordList.add(record);
    }

    @Override
    public UniqueRecordList getUniqueRecordListObject() {
        return recordList;
    }

    @Override
    public ObservableList<Record> getRecordList() {
        return recordList.asUnmodifiableObservableList();
    }

    @Override
    public void setRecordList(UniqueRecordList uniqueRecordLists) {
        CollectionUtil.requireAllNonNull(uniqueRecordLists);
        recordList.setRecords(uniqueRecordLists);
    }

    @Override
    public ObservableList<Record> getFilterRecordList() {
        return filteredRecordList;
    }

    @Override
    public void updateFilteredRecordList(Predicate<Record> predicate) {
        requireNonNull(predicate);
        filteredRecordList.setPredicate(predicate);
    }

    //=========== Statistics List =============================================================

    @Override
    public SimpleStringProperty getAverageType() {
        return averageMap.getInternalAverageType();
    }

    @Override
    public SimpleStringProperty getRecordType() {
        return averageMap.getInternalRecordType();
    }

    @Override
    public void calculateAverageMap(AverageType averageType, RecordType recordType, int count) {
        averageMap.calculateAverage(getRecordList(), averageType, recordType, count);
    }

    @Override
    public ObservableMap<LocalDate, Double> getAverageMap() {
        return averageMap.asUnmodifiableObservableMap();
    }

}
