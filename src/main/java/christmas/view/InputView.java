package christmas.view;

import static christmas.view.message.ErrorMessage.INVALID_BLANK;
import static christmas.view.message.ErrorMessage.INVALID_NAME_COUNT_TYPE;
import static christmas.view.message.ErrorMessage.INVALID_NUMBER_TYPE;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String DASH = "-";
    private static final int VALID_SIZE = 2;

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
            throw new NumberFormatException(INVALID_NUMBER_TYPE.getMessage());
        }
    }

    public List<Map<String, Integer>> readOrders() {
        String input = Console.readLine();
        validateEmptyLine(input);
        return getValidMenus(input);
    }

    public List<Map<String, Integer>> getValidMenus(String orders) {
        return Arrays.stream(orders.split(DELIMITER))
                .map(this::parseNameAndCount)
                .map(this::createValidOrders)
                .toList();
    }

    private List<String> parseNameAndCount(String name) {
        List<String> nameAndCount = Arrays.asList(name.split(DASH));
        validateNameAndCountLength(nameAndCount);
        return nameAndCount;
    }

    private Map<String, Integer> createValidOrders(List<String> namedAndCount) {
        String name = namedAndCount.get(0);
        String count = namedAndCount.get(1);
        int validatedCount = validateCount(count);

        Map<String, Integer> menu = new HashMap<>();
        menu.put(name, validatedCount);
        return menu;
    }

    private void validateNameAndCountLength(List<String> order) {
        if (order.size() != VALID_SIZE) {
            throw new IllegalArgumentException(INVALID_NAME_COUNT_TYPE.getMessage());
        }
    }

    private int validateCount(String count) {
        validateOnlyDigit(count);
        return Integer.parseInt(count);
    }

}
