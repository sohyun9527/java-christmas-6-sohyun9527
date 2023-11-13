package christmas.service;

import christmas.model.EventDate;
import christmas.util.EventDateConverter;

public class ChristmasEventService {
    public EventDate generateEventDate(String input) {
        int validDate = EventDateConverter.convertDate(input);
        return new EventDate(validDate);
    }
}
