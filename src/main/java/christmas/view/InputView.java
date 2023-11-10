package christmas.view;

import static christmas.view.message.ErrorMessage.INVALID_BLANK;
import static christmas.view.message.ErrorMessage.NOT_ONLY_DIGIT;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String DASH = "-";

    public int readVisitDay() {
        String input = Console.readLine();
        validateEmptyLine(input);
        validateOnlyDigit(input);
        return Integer.parseInt(input);
    }

    private void validateEmptyLine(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_BLANK.getMessage());
        }
    }

    public static void validateOnlyDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit) || input.isEmpty()) {
            throw new NumberFormatException(NOT_ONLY_DIGIT.getMessage());
        }
    }
}
