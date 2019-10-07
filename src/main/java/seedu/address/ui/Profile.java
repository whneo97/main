package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the profile information of the user.
 */
public class Profile extends UiPart<Region> {
    private static final String FXML = "Profile.fxml";
    private final Logger logger = LogsCenter.getLogger(Profile.class);

    @FXML
    private ImageView img;
    @FXML
    private Label name;
    @FXML
    private Label asl;

    public Profile(Image img, String name, String asl) {
        super(FXML);
//        this.img.setImage(img);
        this.name.setText(name);
        this.asl.setText(asl);
    }

}
