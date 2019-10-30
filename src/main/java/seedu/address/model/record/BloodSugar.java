package seedu.address.model.record;

import static java.util.Objects.requireNonNull;

import seedu.address.model.DateTime;

/**
 * Represents a Blood Sugar record in the record book. Guarantees: details are present and not null, field values are
 * validated, immutable.
 */
public class BloodSugar extends Record {
    public static final String MESSAGE_CONSTRAINTS = "Bmi can take any postitive values, "
        + "and it should not be blank";

    private final Concentration concentration;

    public BloodSugar(Concentration concentration, DateTime dateTime) {
        super(dateTime);
        requireNonNull(concentration);
        this.concentration = concentration;
    }

    public Concentration getConcentration() {
        return concentration;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Blood Sugar Record :")
            .append(" Concentration: ")
            .append(concentration);
        return builder.toString() + " " + super.toString();
    }
}
