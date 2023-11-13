package christmas.view;

import static christmas.view.message.ErrorMessage.INVALID_BLANK;
import static christmas.view.message.ErrorMessage.INVALID_DATE;
import static christmas.view.message.ErrorMessage.INVALID_ORDER;
import static christmas.view.message.ErrorMessage.INVALID_RESPONSE_TYPE;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView {
    private static final String VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_QUESTION = "총 주문금액 10,000원 부터 이벤트가 적용됩니다! 다시 주문하시겠습니까? (y/n을 입력해주세요!)";
    private static final String DELIMITER = ",";
    private static final String DASH = "-";
    private static final int VALID_SIZE = 2;

    public int readVisitDay() {
        System.out.println(VISIT_DATE_MESSAGE);
        String input = Console.readLine();
        validateEmptyLine(input);
        return getValidateDate(input);
    }

    public boolean readReorder() {
        System.out.println(EVENT_QUESTION);
        String input = Console.readLine();
        validateEmptyLine(input);
        return getValidateResult(input);
    }

    public boolean getValidateResult(String input) {
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_RESPONSE_TYPE.getMessage());
    }

    private void validateEmptyLine(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_BLANK.getMessage());
        }
    }

    private int getValidateDate(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
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
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private int validateCount(String count) {
        validateOnlyDigit(count);
        return Integer.parseInt(count);
    }

    private void validateOnlyDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit) || input.isEmpty()) {
            throw new NumberFormatException(INVALID_ORDER.getMessage());
        }
    }
}
