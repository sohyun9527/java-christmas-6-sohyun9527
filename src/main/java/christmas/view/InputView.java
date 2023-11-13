package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.exception.BlankException;
import java.util.function.Supplier;

public class InputView {
    private static final String VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String getVisitDate() {
        String input = printAndReadInput(VISIT_DATE_MESSAGE);
        validateEmptyLine(input);
        return input;
    }

    public String getOrders() {
        String input = printAndReadInput(REQUEST_ORDER_MESSAGE);
        validateEmptyLine(input);
        return input;
    }

    private void validateEmptyLine(String input) {
        if (input.trim().isEmpty()) {
            throw new BlankException();
        }
    }

    private String printAndReadInput(String message) {
        System.out.println(message);
        return getUserInput();
    }

    private String getUserInput() {
        return Console.readLine();
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
