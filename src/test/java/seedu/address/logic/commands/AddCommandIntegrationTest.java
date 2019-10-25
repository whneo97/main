package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;

import seedu.sugarmummy.model.Model;
import seedu.sugarmummy.model.ModelManager;
import seedu.sugarmummy.model.UserPrefs;
import seedu.sugarmummy.model.bio.UserList;
import seedu.sugarmummy.model.calendar.Calendar;
import seedu.sugarmummy.model.food.UniqueFoodList;
import seedu.sugarmummy.model.record.UniqueRecordList;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new UserList(), new UniqueFoodList(),
                new UniqueRecordList(), new Calendar());
    }

    //    @Test
    //    public void execute_newPerson_success() {
    //        Person validPerson = new PersonBuilder().build();
    //
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new FoodMap(),
    //        new RecordBook());
    //        expectedModel.addPerson(validPerson);
    //
    //        assertCommandSuccess(new AddCommand(validPerson), model,
    //                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    //    }

    //    @Test
    //    public void execute_duplicateRecord_throwsCommandException() {
    //        Person personInList = model.getAddressBook().getPersonList().get(0);
    //        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    //    }

}
