package seedu.sugarmummy.logic.commands.records;

import static java.util.Objects.requireNonNull;

import java.util.StringJoiner;

import seedu.sugarmummy.logic.commands.Command;
import seedu.sugarmummy.logic.commands.CommandResult;
import seedu.sugarmummy.logic.commands.exceptions.CommandException;
import seedu.sugarmummy.model.Model;
import seedu.sugarmummy.model.record.RecordType;
import seedu.sugarmummy.model.statistics.AverageType;
import seedu.sugarmummy.ui.DisplayPaneType;

/**
 * Shows daily/weekly/monthly average of different record types.
 */
public class AverageCommand extends Command {

    public static final String COMMAND_WORD = "average";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows daily/weekly/monthly average of different "
            + "record types.\n"
            + "Parameters: a/AVERAGE_TYPE rt/RECORD_TYPE [n/COUNT]\n"
            + "Example: " + COMMAND_WORD + " a/daily rt/bloodsugar n/5";

    public static final String MESSAGE_INVALID_COUNT = "n/COUNT";

    public static final String MESSAGE_INVALID_AVGTYPE = "a/AVERAGE_TYPE";

    public static final String MESSAGE_INVALID_RECORDTYPE = "rt/RECORD_TYPE";

    public static final String MESSAGE_NO_RECORD = "Sorry! You do not have any %1$s record.";

    private final AverageType averageType;

    private final RecordType recordType;

    private final int count;

    public AverageCommand(AverageType averageType, RecordType recordType, int count) {
        requireNonNull(averageType);
        requireNonNull(recordType);
        this.averageType = averageType;
        this.recordType = recordType;
        this.count = count;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.calculateAverageMap(averageType, recordType, count);

        StringJoiner result = new StringJoiner(System.lineSeparator());

        model.getAverageMap().entrySet().stream()
                .limit(count)
                .forEach(ele -> result.add("average for " + this.recordType + " "
                        + ele.getKey() + " is " + ele.getValue()));

        if (result.toString().isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NO_RECORD, this.recordType));
        }

        return new CommandResult(String.format(result.toString()));
    }

    @Override
    public DisplayPaneType getDisplayPaneType() {
        return DisplayPaneType.AVERAGE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AverageCommand // instanceof handles nulls
                && averageType.equals(((AverageCommand) other).averageType) // state check
                && recordType.equals(((AverageCommand) other).recordType)
                && count == ((AverageCommand) other).count);
    }
}
