package seedu.address.logic.commands.aesthetics;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.aesthetics.Colour;
import seedu.address.ui.DisplayPaneType;

/**
 * Edits the details of an existing user in the address book.
 */
public class FontColourCommand extends Command {

    public static final String COMMAND_WORD = "fontcolour";
    public static final String MESSAGE_SUCCESS = "Font colour has been set!";
    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ": Sets the font colour of this application "
            + "using either CSS colour names or hexadecimal alphanumeric characters representing rgb colours.\n\n"
            + "Parameter: COLOUR\n\n"
            + "Example: fontcolour turquoise\n"
            + "Example fontcolour #00FF00";

    private Colour fontColour;

    public FontColourCommand(Colour fontColour) {
        this.fontColour = fontColour;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Colour previousColour = model.getFontColour();
        Colour newColour = fontColour;
        model.setFontColour(newColour);
        String updateMessage = "Colour has been changed from " + previousColour + " to " + newColour;
        return new CommandResult(MESSAGE_SUCCESS + " " + updateMessage);
    }

    @Override
    public DisplayPaneType getDisplayPaneType() {
        return DisplayPaneType.COLOUR;
    }
}
