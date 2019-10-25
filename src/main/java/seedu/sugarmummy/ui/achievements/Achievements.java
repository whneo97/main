package seedu.sugarmummy.ui.achievements;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import seedu.sugarmummy.ui.UiPart;

/**
 * A ui for the user's list of achievements, displayed in tiles.
 */
public class Achievements extends UiPart<Region> {

    private static final String FXML = "Achievements.fxml";

    @FXML
    private TilePane achievementsTilePane;

    public Achievements() {
        super(FXML);
    }

    public TilePane getTilePane() {
        return achievementsTilePane;
    }
}
