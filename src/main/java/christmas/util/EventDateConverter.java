package christmas.util;

import christmas.model.exception.DateException;

public class EventDateConverter {
    public static int convertDate(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DateException();
        }
    }
}
