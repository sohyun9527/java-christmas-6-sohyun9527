package christmas.model;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Event {
    private static final int YEAR = 2023;
    private final int date;

    public Event(int date) {
        validateDateRange(date);
        this.date = date;
    }

    private void validateDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("1 ~ 31일 중 날짜를 택해주세요.");
        }
    }

    public DayOfWeek getLocalDate() {
        LocalDate localDate = LocalDate.of(YEAR, Month.DECEMBER, date);
        return localDate.getDayOfWeek();
    }
}
