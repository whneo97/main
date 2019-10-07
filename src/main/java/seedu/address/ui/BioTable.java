package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the biography information of the user.
 */
public class BioTable extends UiPart<Region> {
    private static final String FXML = "BioTable.fxml";
    private final Logger logger = LogsCenter.getLogger(BioTable.class);

    @FXML
    private TableColumn field;
    @FXML
    private TableColumn data;

    @FXML
    private ListView<Person> personListView;

    public BioTable() {
        super(FXML);
    }

}
