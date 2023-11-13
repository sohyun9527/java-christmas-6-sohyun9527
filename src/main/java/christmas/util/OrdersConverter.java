package christmas.util;

import christmas.model.exception.OrderException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersConverter {
    private static final String DELIMITER = ",";
    private static final String DASH = "-";
    private static final int VALID_SIZE = 2;

    public static List<Map<String, Integer>> convertOrders(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(OrdersConverter::parseNameAndCount)
                .map(OrdersConverter::createValidOrders)
                .toList();
    }

    public static List<String> parseNameAndCount(String name) {
        List<String> nameAndCount = Arrays.asList(name.split(DASH));
        validateNameAndCountSize(nameAndCount);
        return nameAndCount;
    }

    public static Map<String, Integer> createValidOrders(List<String> namedAndCount) {
        String name = namedAndCount.get(0);
        String count = namedAndCount.get(1);
        int validatedCount = getValidCount(count);

        Map<String, Integer> menu = new HashMap<>();
        menu.put(name, validatedCount);
        return menu;
    }

    public static void validateNameAndCountSize(List<String> order) {
        if (order.size() != VALID_SIZE) {
            throw new OrderException();
        }
    }

    public static int getValidCount(String count) {
        try {
            return Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new OrderException();
        }
    }
}
