package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.exception.BlankException;
import christmas.model.exception.DateException;
import christmas.model.exception.OrderException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class InputView {
    private static final String VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String DELIMITER = ",";
    private static final String DASH = "-";
    private static final int VALID_SIZE = 2;

    public String readVisitDay() {
        System.out.println(VISIT_DATE_MESSAGE);
        String input = Console.readLine();
        validateEmptyLine(input);
        return input;
    }

    private void validateEmptyLine(String input) {
        if (input.trim().isEmpty()) {
            throw new BlankException();
        }
    }

    private int getValidateDate(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DateException();
        }
    }

    public List<Map<String, Integer>> readOrders() {
        System.out.println(REQUEST_ORDER_MESSAGE);
        String input = Console.readLine();
        validateEmptyLine(input);
        return getValidMenus(input);
    }

    private List<Map<String, Integer>> getValidMenus(String orders) {
        return Arrays.stream(orders.split(DELIMITER))
                .map(this::parseNameAndCount)
                .map(this::createValidOrders)
                .toList();
    }

    private List<String> parseNameAndCount(String name) {
        List<String> nameAndCount = Arrays.asList(name.split(DASH));
        validateNameAndCountSize(nameAndCount);
        return nameAndCount;
    }

    private Map<String, Integer> createValidOrders(List<String> namedAndCount) {
        String name = namedAndCount.get(0);
        String count = namedAndCount.get(1);
        int validatedCount = getValidCount(count);

        Map<String, Integer> menu = new HashMap<>();
        menu.put(name, validatedCount);
        return menu;
    }

    private void validateNameAndCountSize(List<String> order) {
        if (order.size() != VALID_SIZE) {
            throw new OrderException();
        }
    }

    private int getValidCount(String count) {
        try {
            return Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new OrderException();
        }
    }

    public static <T> T readUntilValidValue(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
