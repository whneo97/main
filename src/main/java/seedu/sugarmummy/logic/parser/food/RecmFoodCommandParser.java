package seedu.sugarmummy.logic.parser.food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.sugarmummy.logic.commands.food.RecmFoodCommand;
import seedu.sugarmummy.logic.parser.ArgumentMultimap;
import seedu.sugarmummy.logic.parser.ArgumentTokenizer;
import seedu.sugarmummy.logic.parser.CliSyntax;
import seedu.sugarmummy.logic.parser.Flag;
import seedu.sugarmummy.logic.parser.Parser;
import seedu.sugarmummy.logic.parser.exceptions.ParseException;
import seedu.sugarmummy.model.food.FoodNameContainsKeywordsPredicate;
import seedu.sugarmummy.model.food.FoodType;
import seedu.sugarmummy.model.food.FoodTypeIsWantedPredicate;

/**
 * Parses input arguments and creates a new RecmFoodCommand object
 */
public class RecmFoodCommandParser implements Parser<RecmFoodCommand> {

    @Override
    public RecmFoodCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, CliSyntax.PREFIX_FOOD);
        List<FoodType> foodTypes = getWantedFoodTypes(getParsedFlags(argumentMultimap.getPreamble()));
        Optional<String> foodWords = argumentMultimap.getValue(CliSyntax.PREFIX_FOOD);

        if (foodWords.isEmpty()) {
            return new RecmFoodCommand(new FoodTypeIsWantedPredicate(foodTypes), x -> true);
        }

        String[] foodKeywords = foodWords.get().trim().split("\\s+");
        return new RecmFoodCommand(new FoodTypeIsWantedPredicate(foodTypes),
                new FoodNameContainsKeywordsPredicate(Arrays.asList(foodKeywords)));
    }

    private List<Flag> getParsedFlags(String preamble) throws ParseException {
        int expectedFlagNumber = preamble.split("\\s+").length;
        List<Flag> validFlags = Arrays.stream(preamble.split("\\s+"))
                .map(f -> new Flag(f))
                .filter(flag -> CliSyntax.FLAGS.contains(flag))
                .collect(Collectors.toList());

        /*If no flag entered, return all flags to show the full list.*/
        if (preamble.length() == 0) {
            return CliSyntax.FLAGS;
        } else if (expectedFlagNumber != validFlags.size()) {
            throw new ParseException("One or more flags are invalid.");
        } else {
            return validFlags;
        }
    }

    private List<FoodType> getWantedFoodTypes(List<Flag> flags) throws ParseException {

        List<FoodType> foodTypes = new ArrayList<>();
        for (Flag flag : flags) {
            foodTypes.add(FoodType.getFrom(flag.toString()));
        }
        return foodTypes;
    }
}
