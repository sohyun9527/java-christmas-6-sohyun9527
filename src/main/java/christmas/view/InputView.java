package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.exception.BlankException;

public class InputView {

    public String getVisitDate() {
        String input = printAndReadInput(Message.VISIT_DATE_MESSAGE.getMessage());
        validateEmptyLine(input);
        return input;
    }

    public String getOrders() {
        String input = printAndReadInput(Message.REQUEST_ORDER_MESSAGE.getMessage());
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


}
